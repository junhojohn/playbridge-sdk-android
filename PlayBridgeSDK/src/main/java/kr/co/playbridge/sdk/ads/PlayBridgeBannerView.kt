package kr.co.playbridge.sdk.ads

import android.content.Context
import kr.co.playbridge.sdk.internal.impl.PlayBridgeBannerViewImpl

/**
 * 외부(매체 앱)에 공개되는 유일한 배너 API.
 * 실제 로직은 internal 구현체(PlayBridgeBannerViewImpl)에 위임한다.
 */
class PlayBridgeBannerView(context: Context) {

    private val impl = PlayBridgeBannerViewImpl(context)

    fun load() = impl.load()

    fun show() = impl.show()

    fun destroy() = impl.destroy()
}
