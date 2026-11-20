package kr.co.playbridge.commonsdk.internal.interfaces

import android.app.Activity
import android.content.Context
import kr.co.playbridge.commonsdk.api.IOnInitCompleteListener
import kr.co.playbridge.commonsdk.internal.dto.AdRequestInfo

interface IAdLoader {
    fun init(context : Context, iOnInitCompleteListener: IOnInitCompleteListener, adRequestInfo: AdRequestInfo) : Unit
    fun load(activity : Activity, iAdLoaderListener: IAdLoaderListener, adRequestInfo: AdRequestInfo) : Unit
    fun show(activity : Activity, iAdLoaderListener: IAdLoaderListener, adRequestInfo: AdRequestInfo) : Unit
    fun clear(activity : Activity, iAdLoaderListener: IAdLoaderListener, adRequestInfo: AdRequestInfo) : Unit
    fun resume(activity : Activity, iAdLoaderListener: IAdLoaderListener, adRequestInfo: AdRequestInfo) : Unit
    fun pause(activity : Activity, iAdLoaderListener: IAdLoaderListener, adRequestInfo: AdRequestInfo) : Unit
    fun cancel(activity : Activity, iAdLoaderListener: IAdLoaderListener, adRequestInfo: AdRequestInfo) : Unit
    fun destroy(activity : Activity, iAdLoaderListener: IAdLoaderListener, adRequestInfo: AdRequestInfo) : Unit
}