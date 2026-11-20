package kr.co.playbridge.admob.adapter.internal

import android.app.Activity
import android.content.Context
import kr.co.playbridge.commonsdk.api.IOnInitCompleteListener
import kr.co.playbridge.commonsdk.api.IOnInitStatus
import kr.co.playbridge.commonsdk.api.utils.LogUtil
import kr.co.playbridge.commonsdk.internal.dto.AdErrorTypeSpec
import kr.co.playbridge.commonsdk.internal.dto.AdRequestInfo
import kr.co.playbridge.commonsdk.internal.interfaces.IAdLoader
import kr.co.playbridge.commonsdk.internal.interfaces.IAdLoaderListener

class AdmobBannerViewLoaderImpl : IAdLoader {
    init {
        LogUtil.Companion.e("${AdmobBannerViewLoaderImpl::class.java.simpleName}()")
    }
    override fun init(
        context: Context,
        iOnInitCompleteListener: IOnInitCompleteListener,
        adRequestInfo: AdRequestInfo
    ) {
        LogUtil.Companion.e("${AdmobBannerViewLoaderImpl::class.java.simpleName}.init()")
        iOnInitCompleteListener.onComplete(IOnInitStatus.STATE_INIT_SUCCESS)
    }

    override fun load(
        activity: Activity,
        iAdLoaderListener: IAdLoaderListener,
        adRequestInfo: AdRequestInfo
    ) {
        LogUtil.Companion.e("${AdmobBannerViewLoaderImpl::class.java.simpleName}.load()")
        if(adRequestInfo == null) {
            val errorParam = object : AdErrorTypeSpec {
                override val statusCode: Int = 100
                override val statusMessage: String = "Network Timeout Occurred"
                override val errorType = AdErrorTypeSpec.ErrorType.INTERNAL_SDK_ERROR_100
            }
            iAdLoaderListener.onLoadAdFailed(errorParam)
        }else{
            iAdLoaderListener.onLoadAdSuccess()
        }

    }

    override fun show(
        activity: Activity,
        iAdLoaderListener: IAdLoaderListener,
        adRequestInfo: AdRequestInfo
    ) {
        LogUtil.Companion.e("${AdmobBannerViewLoaderImpl::class.java.simpleName}.show()")
    }

    override fun clear(
        activity: Activity,
        iAdLoaderListener: IAdLoaderListener,
        adRequestInfo: AdRequestInfo
    ) {
        LogUtil.Companion.e("${AdmobBannerViewLoaderImpl::class.java.simpleName}.clear()")
    }

    override fun resume(
        activity: Activity,
        iAdLoaderListener: IAdLoaderListener,
        adRequestInfo: AdRequestInfo
    ) {
        LogUtil.Companion.e("${AdmobBannerViewLoaderImpl::class.java.simpleName}.resume()")
    }

    override fun pause(
        activity: Activity,
        iAdLoaderListener: IAdLoaderListener,
        adRequestInfo: AdRequestInfo
    ) {
        LogUtil.Companion.e("${AdmobBannerViewLoaderImpl::class.java.simpleName}.pause()")
    }

    override fun cancel(
        activity: Activity,
        iAdLoaderListener: IAdLoaderListener,
        adRequestInfo: AdRequestInfo
    ) {
        LogUtil.Companion.e("${AdmobBannerViewLoaderImpl::class.java.simpleName}.cancel()")
    }

    override fun destroy(
        activity: Activity,
        iAdLoaderListener: IAdLoaderListener,
        adRequestInfo: AdRequestInfo
    ) {
        LogUtil.Companion.e("${AdmobBannerViewLoaderImpl::class.java.simpleName}.destroy()")
    }


}