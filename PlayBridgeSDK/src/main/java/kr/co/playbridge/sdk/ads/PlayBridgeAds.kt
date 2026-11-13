package kr.co.playbridge.sdk.ads

import android.content.Context
import kr.co.playbridge.sdk.internal.impl.PlayBridgeAdsImpl

class PlayBridgeAds private constructor(){
    companion object {
        fun init(context: Context, onInitCompleteListener: PlayBridgeOnInitCompleteListener) {
            PlayBridgeAdsImpl.init(context, onInitCompleteListener)
        }
    }
}