package kr.co.playbridge.commonsdk.internal.interfaces

import kr.co.playbridge.commonsdk.internal.dto.AdErrorTypeSpec
import org.json.JSONObject

interface IAdLoaderListener {
    fun onLoadAdSuccess()
    fun onLoadAdFailed(adErrorTypeSpec: AdErrorTypeSpec)
    fun onLoadAdDiagnostics(adResponseInfo: JSONObject)
    fun onShowAdSuccess()
    fun onShowAdFailed(adErrorTypeSpec: AdErrorTypeSpec)
    fun onClickAd()
    fun onCloseAd()
    fun onTimeoutAd(adErrorTypeSpec: AdErrorTypeSpec)
    fun onUserRewarded(rewardType : String, rewardAmount : Int, extension : Map<String, Any>)
    fun extension(extension : Map<String, Any>)
}