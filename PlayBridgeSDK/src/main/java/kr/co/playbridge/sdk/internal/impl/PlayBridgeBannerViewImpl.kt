package kr.co.playbridge.sdk.internal.impl

import android.content.Context
import android.widget.LinearLayout
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import kr.co.playbridge.sdk.ads.PlayBridgeLogUtil
import kr.co.playbridge.sdk.internal.viewmodels.AdViewModel

internal class PlayBridgeBannerViewImpl(private val context: Context) : LinearLayout(context) {

    private lateinit var adViewModel: AdViewModel

    fun load() {
        PlayBridgeLogUtil.d("load()")
        val owner = context as? ViewModelStoreOwner
            ?: throw IllegalArgumentException("context must be ViewModelStoreOwner (e.g. ComponentActivity)")
        adViewModel = ViewModelProvider(owner)[AdViewModel::class.java]
        adViewModel.getConfigs()
        adViewModel.configDtoState.observe(context as LifecycleOwner) { configDto ->
            PlayBridgeLogUtil.d( configDto.toString())
        }
    }

    fun show() {
        PlayBridgeLogUtil.d("show()")
    }

    fun destroy() {
        PlayBridgeLogUtil.d("destroy()")
        if (::adViewModel.isInitialized) adViewModel.disposeAll()
    }
}