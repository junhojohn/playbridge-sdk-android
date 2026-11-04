package kr.co.playbridge.sdk.internal.dto

class ConfigDto(_mediaId: String, _placementUid: String) {

    private val mediaId : String = _mediaId
    private val placementUid : String = _placementUid

    override fun toString(): String {
        return "ConfigDto(mediaId='$mediaId', placementUid='$placementUid')"
    }
}