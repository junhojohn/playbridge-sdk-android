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
-repackageclasses 'kr.co.playbridge.admob.adapter.obfuscated'
-allowaccessmodification
#================== Repackage 적용 끝 ==================

#================== Gms Proguard for Release 적용 코드 시작 ==================

-dontwarn com.google.android.gms.**
-keep public class com.google.android.gms.**
-keep class com.google.android.gms.ads.identifier.AdvertisingIdClient{public *;}
-keep class com.google.android.gms.ads.identifier.AdvertisingIdClient$Info{public *;}

# Google User Messaging Platform (UMP) SDK rules
-keep class com.google.android.ump.** { *; }
-keep interface com.google.android.ump.** { *; }

#================== Gms Proguard for Release 적용 코드 끝 ==================

#================== PlayBridge Admob Adapter SDK Proguard for Release 적용 코드 시작 ==================
# 공개 API: 클래스명과 public 멤버만 유지 (private 필드 impl 등은 난독화)
-keep public class kr.co.playbridge.admob.adapter.api.** {
    public *;
}
#================== PlayBridge Admob Adapter SDK Proguard for Release 적용 코드 끝 ==================