#================== RxJava3.0 Proguard for Release 적용 코드 시작 ==================

# io.reactivex 패키지로 시작하는 클래스에서 발생하는 경고는 모두 무시
-dontwarn io.reactivex.**

# com.jakewharton 패키지로 시작하는 클래스에서 발생하는 경고는 모두 무시
-dontwarn com.jakewharton.**

# com.squareup 패키지로 시작하는 클래스에서 발생하는 경고는 모두 무시
-dontwarn com.squareup.**

# RxJava3 타입 유지 (Retrofit CallAdapter를 위해 필수)
-keep class io.reactivex.rxjava3.core.** { *; }
-keep interface io.reactivex.rxjava3.core.** { *; }
-keep class io.reactivex.rxjava3.disposables.** { *; }
-keep interface io.reactivex.rxjava3.disposables.** { *; }
-keep class io.reactivex.rxjava3.schedulers.** { *; }
-keep interface io.reactivex.rxjava3.schedulers.** { *; }

#================== RxJava3.0 Proguard for Release 적용 코드 끝 ==================

#================== Common SDK Proguard for Release 적용 코드 시작 ==================
-keep class kr.co.playbridge.commonsdk.api.** { *; }
#================== Common SDK Proguard for Release 적용 코드 끝 ==================