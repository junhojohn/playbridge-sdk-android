package kr.co.playbridge.sdk.ads

interface PlayBridgeBannerViewListener {
    fun onAdLoaded()

    fun onAdLoadFailed(playBridgeError: PlayBridgeError)

    fun onAdShowed()

    fun onAdShowFailed(playBridgeError: PlayBridgeError)

    fun onShowLandingScreen()

    fun onCloseLandingScreen()

    fun onTimeout(playBridgeError: PlayBridgeError)

    fun onClickAd()
}