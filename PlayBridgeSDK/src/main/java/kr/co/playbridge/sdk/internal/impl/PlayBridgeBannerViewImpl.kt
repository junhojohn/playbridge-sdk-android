package kr.co.playbridge.sdk.internal.impl

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import kr.co.playbridge.sdk.R
import kr.co.playbridge.sdk.ads.PlayBridgeBannerView
import kr.co.playbridge.sdk.ads.PlayBridgeBannerViewListener
import kr.co.playbridge.sdk.ads.PlayBridgeError
import kr.co.playbridge.sdk.ads.PlayBridgeLogUtil
import kr.co.playbridge.sdk.internal.viewmodels.AdViewModel
import java.lang.Exception

@SuppressLint("ViewConstructor")
internal class PlayBridgeBannerViewImpl(private val context: Context, private val playBridgeBannerView: PlayBridgeBannerView) : View.OnLayoutChangeListener{

    private lateinit var adViewModel: AdViewModel

    private lateinit var playBridgeBannerViewListener: PlayBridgeBannerViewListener


    private fun init() {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View? = inflater.inflate(R.layout.playbridge_banner_view, playBridgeBannerView, false)
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

            if(configDto.mediaId.isEmpty() || configDto.placementUid.isEmpty()) {
                playBridgeBannerViewListener.onAdLoadFailed(PlayBridgeError.ERROR_100)
            }else{
                playBridgeBannerViewListener.onAdLoaded()
            }
        }
    }

    fun show() {
        PlayBridgeLogUtil.d("show()")
        try {
            init()
            playBridgeBannerViewListener.onAdShowed()
        }catch (ex : Exception) {
            PlayBridgeLogUtil.e("Internal error occurred.", ex)
            playBridgeBannerViewListener.onAdShowFailed(PlayBridgeError.ERROR_200)
        }
    }

    fun setPlayBridgeBannerViewListener(_playBridgeBannerViewListener: PlayBridgeBannerViewListener) {
        playBridgeBannerViewListener = _playBridgeBannerViewListener
    }

    fun destroy() {
        PlayBridgeLogUtil.d("destroy()")
        if (::adViewModel.isInitialized) adViewModel.disposeAll()

        playBridgeBannerView.removeAllViews()
        playBridgeBannerView.destroy()
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