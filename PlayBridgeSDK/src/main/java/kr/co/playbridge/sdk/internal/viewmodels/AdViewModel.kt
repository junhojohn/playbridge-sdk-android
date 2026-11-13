package kr.co.playbridge.sdk.internal.viewmodels

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kr.co.playbridge.sdk.ads.PlayBridgeLogUtil
import kr.co.playbridge.sdk.internal.dto.ConfigDto
import kr.co.playbridge.sdk.internal.repository.AdRepository

class AdViewModel : ViewModel() {

    private val adRepository = AdRepository()
    private val _configDto = MutableLiveData<ConfigDto>()

    val configDtoState : LiveData<ConfigDto> = _configDto

    private var compositeDisposable = CompositeDisposable()

    @SuppressLint("CheckResult")
    fun getConfigs() {
        PlayBridgeLogUtil.d("getConfigs()")
        compositeDisposable.add(
            adRepository
            .selectConfigs()
            .subscribe(
                {configDto ->
                    _configDto.value = configDto
                },
                {throwable ->
                    _configDto.value = ConfigDto("", "")
                }
            )
        )
    }

    fun disposeAll() {
        PlayBridgeLogUtil.e("disposeAll()")
        if(!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }
}