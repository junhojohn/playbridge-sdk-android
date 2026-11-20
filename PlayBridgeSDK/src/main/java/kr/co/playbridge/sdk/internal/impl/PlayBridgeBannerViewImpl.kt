package kr.co.playbridge.sdk.internal.impl

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import kr.co.playbridge.commonsdk.api.IOnInitCompleteListener
import kr.co.playbridge.commonsdk.api.IOnInitStatus
import kr.co.playbridge.commonsdk.api.utils.LogUtil
import kr.co.playbridge.commonsdk.internal.dto.AdErrorTypeSpec
import kr.co.playbridge.commonsdk.internal.dto.AdRequestInfo
import kr.co.playbridge.commonsdk.internal.interfaces.IAdLoader
import kr.co.playbridge.commonsdk.internal.interfaces.IAdLoaderListener
import kr.co.playbridge.sdk.R
import kr.co.playbridge.sdk.ads.PlayBridgeBannerView
import kr.co.playbridge.sdk.ads.PlayBridgeBannerViewListener
import kr.co.playbridge.sdk.ads.PlayBridgeError
import kr.co.playbridge.sdk.ads.PlayBridgeLogUtil
import kr.co.playbridge.sdk.internal.viewmodels.AdViewModel
import org.json.JSONObject

@SuppressLint("ViewConstructor")
internal class PlayBridgeBannerViewImpl(private val context: Context, private val playBridgeBannerView: PlayBridgeBannerView) : View.OnLayoutChangeListener{

    private lateinit var adViewModel: AdViewModel

    private lateinit var playBridgeBannerViewListener: PlayBridgeBannerViewListener

    private var cnt : Int = 0

    private lateinit var tvCnt : TextView

    init {
        init()
    }

    private val bridgeListener = object : IAdLoaderListener {
        override fun onLoadAdSuccess() {
            playBridgeBannerViewListener.onAdLoaded()
        }

        override fun onLoadAdFailed(adErrorTypeSpec: AdErrorTypeSpec) {
            playBridgeBannerViewListener.onAdLoadFailed(PlayBridgeError.from(adErrorTypeSpec))
        }

        override fun onLoadAdDiagnostics(adResponseInfo: JSONObject) {
            LogUtil.e(adResponseInfo.toString())
        }

        override fun onShowAdSuccess() {
            playBridgeBannerViewListener.onAdShowed()
        }

        override fun onShowAdFailed(adErrorTypeSpec: AdErrorTypeSpec) {
            playBridgeBannerViewListener.onAdShowFailed(PlayBridgeError.from(adErrorTypeSpec))
        }

        override fun onClickAd() {
            playBridgeBannerViewListener.onClickAd()
        }

        override fun onCloseAd() {
            playBridgeBannerViewListener.onCloseLandingScreen()
        }

        override fun onTimeoutAd(adErrorTypeSpec: AdErrorTypeSpec) {
            playBridgeBannerViewListener.onTimeout(PlayBridgeError.from(adErrorTypeSpec))
        }

        override fun onUserRewarded(
            rewardType: String,
            rewardAmount: Int,
            extension: Map<String, Any>
        ) {
            TODO("Not yet implemented")
        }

        override fun extension(extension: Map<String, Any>) {
            TODO("Not yet implemented")
        }

    }
    private fun init() {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View? = inflater.inflate(R.layout.playbridge_banner_view, playBridgeBannerView, false)
        tvCnt = view?.findViewById<TextView>(R.id.tv_cnt)!!
        playBridgeBannerView.addView(view)
        val layoutParams = RelativeLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL)
        playBridgeBannerView.setLayoutParams(layoutParams)
        playBridgeBannerView.addOnLayoutChangeListener(this)

        val curLp = RelativeLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )
        curLp.addRule(RelativeLayout.CENTER_HORIZONTAL)
        val penLp = RelativeLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )
        penLp.addRule(RelativeLayout.CENTER_HORIZONTAL)
    }
    fun load() {
        PlayBridgeLogUtil.d("load()")
        val owner = context as? ViewModelStoreOwner
            ?: throw IllegalArgumentException("context must be ViewModelStoreOwner (e.g. ComponentActivity)")
        adViewModel = ViewModelProvider(owner)[AdViewModel::class.java]
        adViewModel.getConfigs()
        adViewModel.configDtoState.observe(context as LifecycleOwner) { configDto ->
            PlayBridgeLogUtil.d( configDto.toString())

            loadAdmobBanner()
//            if(configDto.mediaId.isEmpty() || configDto.placementUid.isEmpty()) {
//                playBridgeBannerViewListener.onAdLoadFailed(PlayBridgeError.ERROR_100)
//            }else{
//                playBridgeBannerViewListener.onAdLoaded()
//            }
        }
    }

    private fun loadAdmobBanner() {
        val clazz = Class.forName("kr.co.playbridge.admob.adapter.api.AdmobBannerViewLoader")
        val admobBannerViewLoader = clazz.newInstance() as IAdLoader
        admobBannerViewLoader.init(
            context, object : IOnInitCompleteListener {
                override fun onComplete(status: IOnInitStatus?) {
                    LogUtil.e("${status?.statusCode} & ${status?.statusMessage}")
                    admobBannerViewLoader.load(context as Activity, bridgeListener, AdRequestInfo())
                }

            }, AdRequestInfo())
    }

    fun show() {
        PlayBridgeLogUtil.d("show()")
        try {
            tvCnt.text = "${++cnt}"
            playBridgeBannerViewListener.onAdShowed()
        }catch (ex : Exception) {
            PlayBridgeLogUtil.e("Internal error occurred.", ex)
            playBridgeBannerViewListener.onAdShowFailed(PlayBridgeError.ERROR_200)
        }
    }

    fun setPlayBridgeBannerViewListener(_playBridgeBannerViewListener: PlayBridgeBannerViewListener) {
        playBridgeBannerViewListener = _playBridgeBannerViewListener
    }

    private var isDestroyed = false

    fun destroy() {
        if (isDestroyed) return     // 버튼 + onDestroy 등 중복 호출 방지
        isDestroyed = true
        PlayBridgeLogUtil.d("destroy()")
        if (::adViewModel.isInitialized) adViewModel.disposeAll()

        playBridgeBannerView.removeOnLayoutChangeListener(this)
        playBridgeBannerView.removeAllViews()
        // playBridgeBannerView.destroy()는 다시 impl.destroy()를 호출하므로 여기서 부르면 무한 재귀 발생
    }

    override fun onLayoutChange(
        v: View?,
        left: Int,
        top: Int,
        right: Int,
        bottom: Int,
        oldLeft: Int,
        oldTop: Int,
        oldRight: Int,
        oldBottom: Int
    ) {
        PlayBridgeLogUtil.d("onLayoutChange")
    }
}