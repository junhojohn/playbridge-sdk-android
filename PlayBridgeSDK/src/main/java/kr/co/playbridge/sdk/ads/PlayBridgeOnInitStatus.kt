package kr.co.playbridge.sdk.ads

enum class PlayBridgeOnInitStatus(_statusCode : Int, _statusMessage : String) {

    STATE_INIT_SUCCESS(200, "Init success!"),
    STATE_INIT_FAILURE(400, "Init failed...");

    var statusCode : Int = _statusCode
    var statusMessage : String = _statusMessage

    override fun toString(): String {
        return "PlayBridgeStatus(statusCode=$statusCode, statusMessage='$statusMessage')"
    }

    companion object {
        fun getEnum(statusCode : Int) : PlayBridgeOnInitStatus? {
            return PlayBridgeOnInitStatus.entries.find { initStatus -> initStatus.statusCode == statusCode }
        }
    }
}