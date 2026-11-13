package kr.co.playbridge.sdk.ads

enum class PlayBridgeError(_statusCode : Int, _statusMessage : String) {

    ERROR_100(100, "100"),
    ERROR_200(200, "200"),
    ERROR_300(300, "300"),
    ERROR_400(400, "400"),
    ERROR_500(500, "500");

    var statusCode : Int = _statusCode
    var statusMessage : String = _statusMessage


    override fun toString(): String {
        return "PlayBridgeError(statusCode=$statusCode, statusMessage='$statusMessage')"
    }
}