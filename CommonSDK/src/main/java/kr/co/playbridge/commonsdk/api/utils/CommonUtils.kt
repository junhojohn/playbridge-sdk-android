package kr.co.playbridge.commonsdk.api.utils

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import kr.co.playbridge.commonsdk.api.IOnInitCompleteListener
import kr.co.playbridge.commonsdk.api.IOnInitStatus
import kr.co.playbridge.commonsdk.internal.viewmodels.DeviceInfoViewModel

class CommonUtils private constructor() {

    companion object {

        @SuppressLint("CheckResult")
        fun init(context: Context, bridgeListener: IOnInitCompleteListener) {

            val owner = context as? ViewModelStoreOwner
                ?: throw IllegalArgumentException("context must be ViewModelStoreOwner (e.g. ComponentActivity)")
            val deviceInfoViewModel = ViewModelProvider(owner)[DeviceInfoViewModel::class.java]
            deviceInfoViewModel.loadDeviceInfo(context)
            deviceInfoViewModel._deviceInfo.observe(context as LifecycleOwner) { deviceInfo ->
                LogUtil.d("deviceInfo: ${deviceInfo.toString()}")
                if(deviceInfo == null || deviceInfo == "Advertising ID is null.") {
                    bridgeListener.onComplete(IOnInitStatus.STATE_INIT_FAILURE)
                }else{
                    bridgeListener.onComplete(IOnInitStatus.STATE_INIT_SUCCESS)
                }
            }
        }
    }
}