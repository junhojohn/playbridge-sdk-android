package kr.co.playbridge.sdk.internal.repository

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kr.co.playbridge.sdk.ads.PlayBridgeLogUtil
import kr.co.playbridge.sdk.internal.dto.ConfigDto
import java.util.concurrent.TimeUnit

class AdRepository {

    fun selectConfigs() : Observable<ConfigDto> {
        PlayBridgeLogUtil.d("selectConfigs()")

        return Observable
            .interval(0L, 1000L, TimeUnit.MILLISECONDS)
            .switchMap { t ->
                Observable.just(
            ConfigDto("TestMedia$t", "TestPlacement$t")
                ) }
            .subscribeOn(Schedulers.computation())
            .doOnNext { configDto -> PlayBridgeLogUtil.d("doOnNext(${configDto.toString()})") }
            .observeOn(AndroidSchedulers.mainThread())
    }
}