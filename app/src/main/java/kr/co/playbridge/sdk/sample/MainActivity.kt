package kr.co.playbridge.sdk.sample

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kr.co.playbridge.sdk.ads.PlayBridgeAds
import kr.co.playbridge.sdk.ads.PlayBridgeBannerView
import kr.co.playbridge.sdk.ads.PlayBridgeBannerViewListener
import kr.co.playbridge.sdk.ads.PlayBridgeError
import kr.co.playbridge.sdk.ads.PlayBridgeLogUtil
import kr.co.playbridge.sdk.ads.PlayBridgeOnInitCompleteListener
import kr.co.playbridge.sdk.ads.PlayBridgeOnInitStatus

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
        var mainLayout = findViewById<ConstraintLayout>(R.id.main)
        mainLayout.addView(playBridgeBannerView)

        findViewById<Button>(R.id.btn_load).setOnClickListener {
            playBridgeBannerView.load()
            playBridgeBannerView.setPlayBridgeBannerViewListener(object : PlayBridgeBannerViewListener {
                override fun onAdLoaded() {
                    PlayBridgeLogUtil.d("onAdLoaded()")
                }

                override fun onAdLoadFailed(playBridgeError: PlayBridgeError) {
                    PlayBridgeLogUtil.d("onAdLoadFailed(${playBridgeError.toString()})")
                }

                override fun onAdShowed() {
                    PlayBridgeLogUtil.d("onAdShowed()")
                }
                override fun onAdShowFailed(playBridgeError: PlayBridgeError) {
                    PlayBridgeLogUtil.d("onAdShowFailed(${playBridgeError.toString()})")
                }

                override fun onShowLandingScreen() {
                    PlayBridgeLogUtil.d("onShowLandingScreen()")
                }

                override fun onCloseLandingScreen() {
                    PlayBridgeLogUtil.d("onCloseLandingScreen()")
                }

                override fun onTimeout(playBridgeError: PlayBridgeError) {
                    PlayBridgeLogUtil.d("onTimeout(${playBridgeError.toString()})")
                }

                override fun onClickAd() {
                    TODO("Not yet implemented")
                }

            })
        }
        findViewById<Button>(R.id.btn_show).setOnClickListener {
            playBridgeBannerView.show()
        }
        findViewById<Button>(R.id.btn_destroy).setOnClickListener {
            playBridgeBannerView.destroy()
        }

        PlayBridgeAds.init(this, object : PlayBridgeOnInitCompleteListener {
            override fun onComplete(status: PlayBridgeOnInitStatus?) {
                PlayBridgeLogUtil.d("init finish with ${status?.statusCode} , ${status?.statusMessage} ")

            }
        })
    }

    override fun onDestroy() {
        if (::playBridgeBannerView.isInitialized) playBridgeBannerView.destroy()
        super.onDestroy()
    }
}