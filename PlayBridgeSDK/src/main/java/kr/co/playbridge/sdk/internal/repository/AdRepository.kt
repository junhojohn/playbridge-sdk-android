package kr.co.playbridge.sdk.internal.repository

import android.util.Log
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kr.co.playbridge.sdk.internal.dto.ConfigDto
import kr.co.playbridge.sdk.internal.viewmodels.AdViewModel
import java.util.concurrent.TimeUnit

class AdRepository {

    companion object {
        private val TAG = AdRepository::class.simpleName
    }

    fun selectConfigs() : Observable<ConfigDto> {
        Log.e(TAG, "${TAG}.selectConfigs()")

        return Observable
            .interval(0L, 1000L, TimeUnit.MILLISECONDS)
            .switchMap { t ->
                Observable.just(
            ConfigDto("TestMedia$t", "TestPlacement$t")
                ) }
            .subscribeOn(Schedulers.computation())
            .doOnNext { configDto -> Log.e(TAG, "$TAG.doOnNext(${configDto.toString()})") }
            .observeOn(AndroidSchedulers.mainThread())
//        return Observable
//            .just(
//            ConfigDto("TestMedia1", "TestPlacement1"),
//            ConfigDto("TestMedia2", "TestPlacement2"),
//            ConfigDto("TestMedia3", "TestPlacement3")
//            )
//            .subscribeOn(Schedulers.computation())
//            .doOnNext { configDto -> Log.e(TAG, "$TAG.doOnNext($configDto)") }
//            .observeOn(AndroidSchedulers.mainThread())
    }
}