package kr.co.playbridge.sdk.sample

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kr.co.playbridge.sdk.ads.PlayBridgeAds
import kr.co.playbridge.sdk.ads.PlayBridgeBannerView
import kr.co.playbridge.sdk.ads.PlayBridgeLogUtil
import kr.co.playbridge.sdk.ads.PlayBridgeOnInitCompleteListener
import kr.co.playbridge.sdk.ads.PlayBridgeOnInitStatus
import java.util.UUID

class MainActivity : AppCompatActivity() {

    private lateinit var playBridgeBannerView : PlayBridgeBannerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        PlayBridgeLogUtil.setLogLevel(PlayBridgeLogUtil.Companion.LogLevel.Verbose)
        PlayBridgeLogUtil.d("debug")
        PlayBridgeLogUtil.e("error")
        PlayBridgeLogUtil.w("warn")
        PlayBridgeLogUtil.i("info")

        playBridgeBannerView = PlayBridgeBannerView(this);

        PlayBridgeAds.init(this, object : PlayBridgeOnInitCompleteListener {
            override fun onComplete(status: PlayBridgeOnInitStatus?) {
                PlayBridgeLogUtil.d("init finish with ${status?.statusCode} , ${status?.statusMessage} ")
                playBridgeBannerView.load()
                playBridgeBannerView.show()
            }
        })
    }

    override fun onDestroy() {
        if (::playBridgeBannerView.isInitialized) playBridgeBannerView.destroy()
        super.onDestroy()
    }
}