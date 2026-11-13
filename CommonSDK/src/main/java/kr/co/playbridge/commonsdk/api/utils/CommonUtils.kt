package kr.co.playbridge.commonsdk.api.utils

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kr.co.playbridge.commonsdk.internal.CommonUtilsImpl
class CommonUtils private constructor() {

    companion object {
        private val adId : MutableLiveData<String> = MutableLiveData()
        val _adId : LiveData<String> = adId
        @SuppressLint("CheckResult")
        fun asyncGetAdId(context: Context) {
            CommonUtilsImpl.asyncGetAdId(context).subscribe (
                { result -> adId.value = result},
                { error -> adId.value = "Advertising ID is null."}
            )
        }
    }
}