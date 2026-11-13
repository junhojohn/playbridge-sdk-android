package kr.co.playbridge.commonsdk.api.utils

import android.util.Log
import kr.co.playbridge.commonsdk.BuildConfig
import kr.co.playbridge.commonsdk.internal.Const

class LogUtil private constructor() {

    companion object {
        @JvmField var logLevel: LogLevel = LogLevel.Warn

        /**
         * 로그 표시 설정 Level
         */
        enum class LogLevel {
            None, Error, Warn, Info, Debug, Verbose
        }

        /**
         * 로그 표시 레벨 설정
         * 로그 수준 지정 : 로그의 상세함 순서는 다음과 같다.
         * LogLevel.None < LogLevel.Error < LogLevel.Warn < LogLevel.Info < LogLevel.Debug < LogLevel.Verbose
         * LogLevel.None 은 모든로그표시 x
         * LogLevel.Verbose 는 모든로그표시
         * @param level 로그 레벨
         */
        fun setLogLevel(level: LogLevel) {
            // 개발모드가 아니면, debug level 이상의 로그는 남기지 않는다.
            if (!BuildConfig.DEBUG && level.ordinal >= LogLevel.Debug.ordinal) {
                logLevel = LogLevel.Info
            } else {
                logLevel = level
            }
        }

        /**
         * 로그 표시 레벨 설정
         * 오직 config 응답의 enable_debug를 통한 사용케이스만을 위한 메서드
         * 만약 isDebugModeFromConfig가 true이면 사용자가 지정한 레벨 setLogLevel()과 상관없이 무조건 debug모드로 레벨이 변경된다.
         * @param isDebugModeFromConfig config 응답의 enable_debug를 boolean형 으로 치환한 플래그 값
         */
        fun setLogLevel(isDebugModeFromConfig: Boolean) {
            if (isDebugModeFromConfig) {
                logLevel = LogLevel.Verbose
                d("enable_debug 모드 활성화 on.")
            }
        }

        /**
         * 현재 설정된 로그 레벨을 리턴
         *
         * @return 현재의 로그 레벨
         */
        fun getLogLevel(): LogLevel {
            return logLevel
        }

        /**
         * It is not allowed to create new instance by user accidentally.
         */
        private fun LogUtils() {}

        /**
         * DEBUG 로그 기록 메소드
         * @param msg 로그 내용
         */
        fun d(msg: String?) {
            if (logLevel.ordinal >= LogLevel.Debug.ordinal) {
                Log.d(Const.LOG_TAG, buildLogMsg(msg))
            }
        }

        /**
         * INFO 로그 기록 메소드
         * @param msg 로그 내용
         */
        fun i(msg: String?) {
            if (logLevel.ordinal >= LogLevel.Info.ordinal) {
                Log.i(Const.LOG_TAG, buildLogMsg(msg))
            }
        }

        /**
         * WARN 로그 기록 메소드
         * @param msg 로그 내용
         */
        fun w(msg: String?) {
            if (logLevel.ordinal >= LogLevel.Warn.ordinal) {
                Log.w(Const.LOG_TAG, buildLogMsg(msg))
            }
        }

        fun w(msg: String?, e: Exception?) {
            if (logLevel.ordinal >= LogLevel.Warn.ordinal) {
                Log.w(Const.LOG_TAG, buildLogMsg(msg), e)
            }
        }

        /**
         * ERROR 로그 기록 메소드
         * @param msg 로그 내용
         */
        fun e(msg: String?) {
            if (logLevel.ordinal >= LogLevel.Error.ordinal) {
                Log.e(Const.LOG_TAG, buildLogMsg(msg))
            }
        }

        fun e(msg: String?, e: Exception?) {
            if (logLevel.ordinal >= LogLevel.Error.ordinal) {
                Log.e(Const.LOG_TAG, buildLogMsg(msg), e)
            }
        }

        /**
         * ERROR 로그 기록 메소드
         * @param msg 로그 내용
         * @param t Throwable 객체
         */
        fun e(msg: String?, t: Throwable?) {
            if (logLevel.ordinal >= LogLevel.Error.ordinal) {
                Log.e(Const.LOG_TAG, buildLogMsg(msg), t)
            }
        }

        private fun buildLogMsg(message: String?): String {
            val ste = Thread.currentThread().getStackTrace()[5]
            val sb = StringBuilder()
            sb.append(message)
            sb.append(Const.BLANK)
            sb.append("@")
            sb.append(ste.className)
            sb.append(".")
            sb.append(ste.methodName)
            sb.append(Const.BLANK)
            sb.append("in")
            sb.append(Const.BLANK)
            sb.append(ste.lineNumber)
            sb.append(Const.BLANK)
            sb.append("line.")


            return sb.toString()
        }
    }
}