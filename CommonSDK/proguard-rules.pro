# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
#================== Repackage 적용 시작 ==================
# keep 대상이 아닌 클래스는 전부 이 패키지 하나로 모아서 a, b, c... 로 이름 변경
# PlayBridgeSDK와 같은 패키지를 쓰면 a.class 등이 겹쳐 앱 빌드 시 Duplicate class 에러 발생 -> 모듈별로 분리
-repackageclasses 'kr.co.playbridge.commonsdk.obfuscated'
-allowaccessmodification
#================== Repackage 적용 끝 ==================

#================== AndroidX ViewModel 적용 시작 ==================
# ViewModelProvider가 리플렉션으로 기본 생성자를 호출하므로 생성자만 유지 (클래스명은 난독화 허용)
-keepclassmembers,allowobfuscation class * extends androidx.lifecycle.ViewModel {
    <init>();
}
#================== AndroidX ViewModel 적용 끝 ==================

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
# 공개 API: 클래스명과 public 멤버만 유지 (private 필드 impl 등은 난독화)
-keep public class kr.co.playbridge.commonsdk.api.** {
    public *;
}
#================== Common SDK Proguard for Release 적용 코드 끝 ==================