
#================== Gms Proguard for Release 적용 코드 시작 ==================

-dontwarn com.google.android.gms.**
-keep public class com.google.android.gms.**
-keep class com.google.android.gms.ads.identifier.AdvertisingIdClient{public *;}
-keep class com.google.android.gms.ads.identifier.AdvertisingIdClient$Info{public *;}

# Google User Messaging Platform (UMP) SDK rules
-keep class com.google.android.ump.** { *; }
-keep interface com.google.android.ump.** { *; }

#================== Gms Proguard for Release 적용 코드 끝 ==================

#================== PlayBridge Common SDK Proguard for Release 적용 코드 시작 ==================
-keep class kr.co.playbridge.commonsdk.api.** { *; }
#================== PlayBridge Common SDK Proguard for Release 적용 코드 끝 ==================

#================== PlayBridge Admob Adapter SDK Proguard for Release 적용 코드 시작 ==================
-keep class kr.co.playbridge.admob.adapter.api.** { *; }
#================== PlayBridge Admob Adapter SDK Proguard for Release 적용 코드 끝 ==================