package kr.co.playbridge.sdk.ads

import kr.co.playbridge.commonsdk.api.utils.LogUtil

class PlayBridgeLogUtil private constructor(){
    companion object {

        @JvmField var logLevel: LogLevel = LogLevel.Warn

        enum class LogLevel {
            None, Error, Warn, Info, Debug, Verbose
        }

        /**
         * 로그 표시 설정
         * Admize 로그 수준 지정 : 로그의 상세함 순서는 다음과 같다.
         * LogLevel.None < LogLevel.Error < LogLevel.Warn < LogLevel.Info < LogLevel.Debug < LogLevel.Verbose
         * LogLevel.None 은 모든로그표시 x
         * LogLevel.Verbose 는 모든로그표시
         * @param level 로그 레벨
         */
        fun setLogLevel(level: LogLevel) {
            logLevel = level
            var coreLogUtilLevel: LogUtil.Companion.LogLevel = LogUtil.Companion.LogLevel.Verbose
            when (logLevel) {
                LogLevel.None -> coreLogUtilLevel = LogUtil.Companion.LogLevel.None
                LogLevel.Error -> coreLogUtilLevel = LogUtil.Companion.LogLevel.Error
                LogLevel.Warn -> coreLogUtilLevel = LogUtil.Companion.LogLevel.Warn
                LogLevel.Info -> coreLogUtilLevel = LogUtil.Companion.LogLevel.Info
                LogLevel.Debug -> coreLogUtilLevel = LogUtil.Companion.LogLevel.Debug
                LogLevel.Verbose -> {}
            }
            LogUtil.setLogLevel(coreLogUtilLevel)
        }
        /**
         * 현재 설정된 로그 레벨을 리턴
         *
         * @return 현재의 로그 레벨
         */
        fun getLogLevel(): LogLevel {
            when (LogUtil.getLogLevel()) {
                LogUtil.Companion.LogLevel.None -> return LogLevel.None
                LogUtil.Companion.LogLevel.Error -> return LogLevel.Error
                LogUtil.Companion.LogLevel.Warn -> return LogLevel.Warn
                LogUtil.Companion.LogLevel.Info -> return LogLevel.Info
                LogUtil.Companion.LogLevel.Debug -> return LogLevel.Debug
                LogUtil.Companion.LogLevel.Verbose -> return LogLevel.Verbose
            }
        }

        /**
         * DEBUG 로그 기록 메소드
         * @param msg 로그 내용
         */
        fun d(msg: String?) {
            LogUtil.d(msg)
        }

        /**
         * INFO 로그 기록 메소드
         * @param msg 로그 내용
         */
        fun i(msg: String?) {
            LogUtil.i(msg)
        }

        /**
         * WARN 로그 기록 메소드
         * @param msg 로그 내용
         */
        fun w(msg: String?) {
            LogUtil.w(msg)
        }

        /**
         * ERROR 로그 기록 메소드
         * @param msg 로그 내용
         */
        fun e(msg: String?) {
            LogUtil.e(msg)
        }

        fun e(msg: String?, e: Exception?) {
            LogUtil.e(msg, e)
        }

        /**
         * ERROR 로그 기록 메소드
         * @param msg 로그 내용
         * @param t Throwable 객체
         */
        fun e(msg: String?, t: Throwable?) {
            LogUtil.e(msg, t)
        }
    }
}