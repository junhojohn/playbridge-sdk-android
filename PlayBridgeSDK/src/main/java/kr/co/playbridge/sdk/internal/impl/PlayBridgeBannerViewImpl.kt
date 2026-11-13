package kr.co.playbridge.sdk.internal.impl

import android.content.Context
import android.util.Log
import android.widget.LinearLayout
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import kr.co.playbridge.sdk.internal.dto.ConfigDto
import kr.co.playbridge.sdk.internal.viewmodels.AdViewModel

internal class PlayBridgeBannerViewImpl(private val context: Context) : LinearLayout(context) {

    companion object {
        private const val TAG = "PlayBridgeBannerViewImpl"
    }

    private lateinit var adViewModel: AdViewModel

    fun load() {
        Log.e(TAG, "$TAG.load()")
        val owner = context as? ViewModelStoreOwner
            ?: throw IllegalArgumentException("context must be ViewModelStoreOwner (e.g. ComponentActivity)")
        adViewModel = ViewModelProvider(owner)[AdViewModel::class.java]
        adViewModel.getConfigs()
        adViewModel.configDtoState.observe(context as LifecycleOwner) { configDto ->
            Log.e(TAG, configDto.toString())
        }
    }

    fun show() {
        Log.e(TAG, "$TAG.show()")
    }

    fun destroy() {
        Log.e(TAG, "$TAG.destroy()")
        if (::adViewModel.isInitialized) adViewModel.disposeAll()
    }
}