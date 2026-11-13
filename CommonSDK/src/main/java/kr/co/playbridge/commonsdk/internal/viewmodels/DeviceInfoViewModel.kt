package kr.co.playbridge.commonsdk.internal.viewmodels

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kr.co.playbridge.commonsdk.internal.repository.AdIdRepository

class DeviceInfoViewModel : ViewModel() {
    private val deviceInfo : MutableLiveData<String> = MutableLiveData()
    val _deviceInfo : LiveData<String> = deviceInfo

    private val adIdRepository : AdIdRepository = AdIdRepository()

    @SuppressLint("CheckResult")
    fun loadDeviceInfo(context: Context) {
        adIdRepository.asyncGetIdfaOrAppSetId(context).subscribe (
            { result -> deviceInfo.value = result},
            { error -> deviceInfo.value = "Advertising ID is null."}
        )
    }
}