package kr.co.playbridge.sdk.internal.viewmodels

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kr.co.playbridge.sdk.internal.dto.ConfigDto
import kr.co.playbridge.sdk.internal.repository.AdRepository

class AdViewModel : ViewModel() {
    companion object{
        private val TAG = AdViewModel::class.simpleName
    }
    private val adRepository = AdRepository()
    private val _configDto = MutableLiveData<ConfigDto>()

    val configDtoState : LiveData<ConfigDto> = _configDto

    private var compositeDisposable = CompositeDisposable()

    @SuppressLint("CheckResult")
    fun getConfigs() {
        Log.e(TAG, "$TAG.getConfigs()")
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
        Log.e(TAG, "$TAG.disposeAll()")
        if(!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }
}