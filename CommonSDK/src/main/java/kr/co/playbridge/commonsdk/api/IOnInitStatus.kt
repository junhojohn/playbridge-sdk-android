package kr.co.playbridge.commonsdk.api

enum class IOnInitStatus(_statusCode : Int, _statusMessage : String) {

    STATE_INIT_SUCCESS(200, "Init success!"),
    STATE_INIT_FAILURE(400, "Init failed...");

    var statusCode : Int = _statusCode
    var statusMessage : String = _statusMessage

    override fun toString(): String {
        return "IOnInitStatus(statusCode=$statusCode, statusMessage='$statusMessage')"
    }

}