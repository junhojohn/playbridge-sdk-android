package kr.co.playbridge.sdk.internal.impl

import android.content.Context
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import kr.co.playbridge.commonsdk.api.utils.CommonUtils
import kr.co.playbridge.sdk.ads.PlayBridgeOnInitCompleteListener
import kr.co.playbridge.sdk.ads.PlayBridgeOnInitStatus

internal class PlayBridgeAdsImpl private constructor(){
    companion object {
        private val TAG = PlayBridgeAdsImpl::class.java.simpleName

        fun init(context: Context, onInitCompleteListener: PlayBridgeOnInitCompleteListener) {
            CommonUtils.asyncGetAdId(context)
            CommonUtils._adId.observe(context as LifecycleOwner) { adId ->
                Log.e(TAG, "adId: ${adId}")
                if(adId == null || adId == "Advertising ID is null.") {
                    onInitCompleteListener.onComplete(PlayBridgeOnInitStatus.STATE_INIT_FAILURE)
                }else{
                    onInitCompleteListener.onComplete(PlayBridgeOnInitStatus.STATE_INIT_SUCCESS)
                }
            }
        }
    }
}