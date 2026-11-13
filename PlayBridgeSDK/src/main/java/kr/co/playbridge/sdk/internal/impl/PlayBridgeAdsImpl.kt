package kr.co.playbridge.sdk.internal.impl

import android.content.Context
import kr.co.playbridge.commonsdk.api.IOnInitStatus
import kr.co.playbridge.commonsdk.api.IOnInitCompleteListener
import kr.co.playbridge.commonsdk.api.utils.CommonUtils
import kr.co.playbridge.sdk.ads.PlayBridgeOnInitCompleteListener
import kr.co.playbridge.sdk.ads.PlayBridgeOnInitStatus

internal class PlayBridgeAdsImpl private constructor(){
    companion object {

        fun init(context: Context, onInitCompleteListener: PlayBridgeOnInitCompleteListener) {
            CommonUtils.init(context, object : IOnInitCompleteListener {
                override fun onComplete(status: IOnInitStatus?) {
                    onInitCompleteListener.onComplete(PlayBridgeOnInitStatus.getEnum(status?.statusCode
                        ?: PlayBridgeOnInitStatus.STATE_INIT_FAILURE.statusCode))
                }
            })
        }
    }
}