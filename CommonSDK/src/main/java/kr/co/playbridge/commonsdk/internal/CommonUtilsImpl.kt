package kr.co.playbridge.commonsdk.internal

import android.content.Context
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.google.android.gms.appset.AppSet
import com.google.android.gms.appset.AppSetIdInfo
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Tasks
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

internal class CommonUtilsImpl private constructor(){
    companion object {

        fun asyncGetAdId(context: Context) : Single<String> {
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
                adId ?: "Advertising ID is null."
            }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())


        }
    }
}