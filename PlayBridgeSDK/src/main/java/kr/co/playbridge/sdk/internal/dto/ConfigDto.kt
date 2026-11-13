package kr.co.playbridge.sdk.internal.dto

class ConfigDto(_mediaId: String, _placementUid: String) {

    val mediaId : String = _mediaId
    val placementUid : String = _placementUid


    override fun toString(): String {
        return "ConfigDto(mediaId='$mediaId', placementUid='$placementUid')"
    }
}