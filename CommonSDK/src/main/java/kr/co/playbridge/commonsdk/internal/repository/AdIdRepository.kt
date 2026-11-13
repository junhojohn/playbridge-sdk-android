package kr.co.playbridge.commonsdk.internal.repository

import android.content.Context
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.google.android.gms.appset.AppSet
import com.google.android.gms.tasks.Tasks
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class AdIdRepository {

    private val compositeDisposable : CompositeDisposable = CompositeDisposable()

    fun asyncGetIdfaOrAppSetId(context: Context) : Single<String> {
        //TODO 구현
        return Single.fromCallable {
            val info = AdvertisingIdClient.getAdvertisingIdInfo(context)
            val id = info.id
            val adId = if (info.isLimitAdTrackingEnabled || id == null ||
                id == "00000000-0000-0000-0000-000000000000") {
                Tasks.await(AppSet.getClient(context).appSetIdInfo).id   // 백그라운드 스레드라 블로킹 OK
            } else {
                id
            }
            adId
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())


    }

    fun disposeAll() {
        compositeDisposable.dispose()
    }
}