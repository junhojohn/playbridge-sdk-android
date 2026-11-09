package kr.co.playbridge.sdk.sample

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kr.co.playbridge.sdk.ads.PlayBridgeBannerView
import java.util.UUID

class MainActivity : AppCompatActivity() {
    companion object {
        private val TAG = MainActivity::class.simpleName
    }
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

        playBridgeBannerView = PlayBridgeBannerView(this);

        playBridgeBannerView.load()
        playBridgeBannerView.show()

        Log.e(TAG, UUID.randomUUID().toString())


    }

    override fun onDestroy() {
        if (::playBridgeBannerView.isInitialized) playBridgeBannerView.destroy()
        super.onDestroy()
    }
}