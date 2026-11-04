package kr.co.playbridge.sdk.sample

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kr.co.playbridge.sdk.ads.PlayBridgeBannerView
import java.util.UUID

class MainActivity : AppCompatActivity() {
    companion object {
        private val TAG = MainActivity::class.simpleName
    }
    private lateinit var playBridgeBannerView : PlayBridgeBannerView
    private var scenarioThread: Thread? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        playBridgeBannerView = PlayBridgeBannerView(this);
//
//        playBridgeBannerView.load()
//        playBridgeBannerView.show()
//
//        Log.e(TAG, UUID.randomUUID().toString())


        val endpoint = "https://sdk.takehome.bagelcode.com/events"
        val candidateKey = "junhojohn@naver.com"
        val sessionId = UUID.randomUUID().toString()
        val eventSender = EventSender()

        Log.e("ThreadInfo", Thread.currentThread().name)
        // ScenarioLoop.run()은 Thread.sleep()으로 블로킹하므로 메인 스레드에서 호출하면 ANR 발생 -> 별도 스레드에서 실행
        scenarioThread = Thread({
            try {
                ScenarioLoop.run(
                    eventSender::init,
                    eventSender,
                    endpoint,
                    candidateKey,
                    sessionId,
                    1010,    // count
                    0,      // intervalMs
                    60,     // observeSeconds
                    null    // valueFactory (null이면 UUID 기반 값 자동 생성)
                )
                Log.d(TAG, "ScenarioLoop finished")
            } catch (e: InterruptedException) {
                Log.w(TAG, "ScenarioLoop interrupted")
            }
        }, "ScenarioLoop").apply { start() }


    }

    override fun onDestroy() {
        scenarioThread?.interrupt()
        if (::playBridgeBannerView.isInitialized) playBridgeBannerView.destroy()
        super.onDestroy()
    }
}