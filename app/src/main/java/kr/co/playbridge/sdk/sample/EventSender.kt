package kr.co.playbridge.sdk.sample

import android.util.Log
import java.util.Queue
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.Executors

/**
 * [track 메서드 설계]
 * ------------------------------[1단계 큐 구현 시작]------------------------------
 * 1. 큐에는 properties를 넣는다.
 * - endpoint, candiateKey는 어차피 불변이기 때문에 신경안써도 됨
 * - sessionId는 어차피 EventSender 객체 안에서 유일성이 보장되기 때문에 항상 같은 값이기 때문에 신경안써도 됨
 * 2.큐 넣을 때 발생할 수 있는 케이스를 생각
 * - queue 사이즈 <= 1000 : return true
 * - queue 사이즈 > 1000 : return false
 * 3. 가장 단순하게 구현방법
 * - 1000 크기의 queue 만들고 들어오는 순서대로 추가 (add)
 * - 큐 추가하고 나서 사이즈 비교 : return true or false
 * - 큐 추가 중에 다른 새로운 쓰레드에서 동일한 큐에 접근해서 수정을 하려고 했을 때 가시성과 원자성이 보장되는지
 *
 * 4. 제한사항
 * - 큐에 추가하다가 어떤 익셉션이 발생하면 그냥 false 반환되고, false 반환된 이벤트는 누락이 될 수 있음.
 *      - 이에 대한 처리로는 파일로 누락 이벤트를 모아서 저장해두었다가 background에서 다시 보내주는 등의 작업이 필요할 수 있음.
 *      - 이건 과제 범위를 벗어나므로 우선 제외시켰음
 *      - 예시 큐에 1000개가 꽉차서 java.lang.IllegalStateException: Queue full발생하는 경우 false 반환 및 이벤트 누락될 수 있음
 * ------------------------------[1단계 큐 구현 끝]------------------------------
 *
 *
 *
 * ------------------------------[2단계 큐 구현 개선 시작]------------------------------
 * 1. 큐에서 요소만 반환하나 큐에서 제거하지 않고 그대로 유지(peek)
 * - poll을 하지 않고 Peek를 한 이유는 [동작요구-용량]에 전송중인 이벤트+미확인 이벤트는 최대 1000개라고 했기 때문임. 따라서 지금 큐에 뽑으려고 하는 원소도 크기에 포함되어야 하기에, 큐에서 바로 제거하지 않고 그대로 유지.
 * 2. 네트워크 요청
 * 3. HTTP 200 + 이벤트 ID를 받고 나면 큐에서 요소 제거 (poll)
 * - 만약 큐를 제거할 때 다른 새로운 쓰레드에서 했을 때 큐의 가시성과 원자성이 보장되는지: 왜냐하면 ScenarioLoop를 돌리기위한 쓰레드를 이미 돌리면서 여기서 큐에 추가하고 + 네트워크는 또다른 쓰레드를 생성하여 비동기 네트워크 통신을 할 것이기 때문. 이때 ScenarioLoop 쓰레드에서도 큐에 접근해서 추가하고, 네트워크 쓰레드에서도 큐에 접근해 제거하려고 한다면 가시성, 원자성이 문제될 수 있음
 * - 따라서 큐 자체를 쓰레드 세이프한 걸로 선택하는게 중요
 * 4. 실패한 경우(그 외의 경우) 큐를 그대로 유지
 * 5. 큐는 static하게 관리되어야 할 것 같다.
 * - 세션별로 생성되는 이벤트를 static 큐에 담아야 하기 때문이다.
 * - 세션별로 생성된다는 의미 == ScenarioLoop 객체를 생성이고
 * - 세션별로 이벤트가 생성되기 때문.
 *
 * ------------------------------[2단계 큐 구현 개선 시작]------------------------------
 *
 *
 *
 *
 * ------------------------------[1단계 구현 시작]------------------------------
 * 1. 기본 network 연결 구조를 구현
 * 2. 장애가 발생할 수 있는 케이스를 생각
 * - 요청 X
 * - 요청 O, 응답 X
 * - 요청 O, 응답 O : Http 200 코드 + 응답에 testevent_가 확인될 때만
 * 3. 테스트
 * - 로컬에서 서버 접속하지 않고 무작위로 케이스를 발생 시켜서 단위테스트할 예정: 그 이유는 테스트를 위해 비용을 최소한으로 발생시키고 싶음
 * ------------------------------[1단계 구현 끝]------------------------------
 *
 *
 */
class EventSender : ScenarioLoop.TrackFunction {

    companion object {
        private const val TAG = "EventSender"
        private val executorService = Executors.newSingleThreadExecutor()
    }

    private lateinit var endpoint : String
    private lateinit var candidateKey : String
    private lateinit var sessionId : String

    private var queue = ArrayBlockingQueue<Map<String, String>>(1000)

    /** ScenarioLoop의 init 단계에서 호출됨 (config: endpoint, candidateKey, sessionId) */
    fun init(config: Map<String, String>) {
        config.run {
            if (contains("endpoint") && get("endpoint") != null)         endpoint = get("endpoint").toString()            else throw Exception("endpoint is null.")
            if (contains("candidateKey") && get("candidateKey") != null) candidateKey = get("candidateKey").toString()    else throw Exception("candidateKey is null.")
            if (contains("sessionId") && get("sessionId") != null)       sessionId = get("sessionId").toString()          else throw Exception("sessionId is null.")
        }
    }

    /** 큐 적재 성공 여부를 반환 */
    override fun track(name: String, properties: Map<String, String>): Boolean {
        try {
            properties.run {
                val value = get("value").toString()
                Log.d(TAG, "endpoint: ${endpoint}, candidateKey: ${candidateKey}, sessionId: ${sessionId}, track: $name $value")
            }
            queue.add(properties)
            return true
        }catch (ex: Exception) {
            Log.e(TAG, ex.toString())
            return false
        }

    }
}
