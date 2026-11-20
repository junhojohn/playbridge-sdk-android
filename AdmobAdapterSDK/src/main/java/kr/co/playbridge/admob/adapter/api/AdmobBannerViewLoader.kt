package kr.co.playbridge.admob.adapter.api

import android.app.Activity
import android.content.Context
import kr.co.playbridge.admob.adapter.internal.AdmobBannerViewLoaderImpl
import kr.co.playbridge.commonsdk.api.IOnInitCompleteListener
import kr.co.playbridge.commonsdk.api.utils.LogUtil
import kr.co.playbridge.commonsdk.internal.dto.AdRequestInfo
import kr.co.playbridge.commonsdk.internal.interfaces.IAdLoader
import kr.co.playbridge.commonsdk.internal.interfaces.IAdLoaderListener

class AdmobBannerViewLoader  : IAdLoader {

    private val impl = AdmobBannerViewLoaderImpl()

    init {
        LogUtil.e("${AdmobBannerViewLoader::class.java.simpleName}()")
    }
    override fun init(
        context: Context,
        iOnInitCompleteListener: IOnInitCompleteListener,
        adRequestInfo: AdRequestInfo
    ) {
        LogUtil.e("${AdmobBannerViewLoader::class.java.simpleName}.init()")
        impl.init(context, iOnInitCompleteListener, adRequestInfo)
    }

    override fun load(
        activity: Activity,
        iAdLoaderListener: IAdLoaderListener,
        adRequestInfo: AdRequestInfo
    ) {
        LogUtil.e("${AdmobBannerViewLoader::class.java.simpleName}.load()")
        impl.load(activity, iAdLoaderListener, adRequestInfo)
    }

    override fun show(
        activity: Activity,
        iAdLoaderListener: IAdLoaderListener,
        adRequestInfo: AdRequestInfo
    ) {
        LogUtil.e("${AdmobBannerViewLoader::class.java.simpleName}.show()")
        impl.show(activity, iAdLoaderListener, adRequestInfo)
    }

    override fun clear(
        activity: Activity,
        iAdLoaderListener: IAdLoaderListener,
        adRequestInfo: AdRequestInfo
    ) {
        LogUtil.e("${AdmobBannerViewLoader::class.java.simpleName}.clear()")
        impl.clear(activity, iAdLoaderListener, adRequestInfo)
    }

    override fun resume(
        activity: Activity,
        iAdLoaderListener: IAdLoaderListener,
        adRequestInfo: AdRequestInfo
    ) {
        LogUtil.e("${AdmobBannerViewLoader::class.java.simpleName}.resume()")
        impl.resume(activity, iAdLoaderListener, adRequestInfo)
    }

    override fun pause(
        activity: Activity,
        iAdLoaderListener: IAdLoaderListener,
        adRequestInfo: AdRequestInfo
    ) {
        LogUtil.e("${AdmobBannerViewLoader::class.java.simpleName}.pause()")
        impl.pause(activity, iAdLoaderListener, adRequestInfo)
    }

    override fun cancel(
        activity: Activity,
        iAdLoaderListener: IAdLoaderListener,
        adRequestInfo: AdRequestInfo
    ) {
        LogUtil.e("${AdmobBannerViewLoader::class.java.simpleName}.cancel()")
        impl.cancel(activity, iAdLoaderListener, adRequestInfo)
    }

    override fun destroy(
        activity: Activity,
        iAdLoaderListener: IAdLoaderListener,
        adRequestInfo: AdRequestInfo
    ) {
        LogUtil.e("${AdmobBannerViewLoader::class.java.simpleName}.destroy()")
        impl.destroy(activity, iAdLoaderListener, adRequestInfo)
    }
}