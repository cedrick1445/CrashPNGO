package tg.craps.pngogames.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import tg.craps.pngogames.di.AppModule
import dagger.hilt.android.AndroidEntryPoint
import tg.craps.pngogames.databinding.ActivityGameContentActvityBinding

@AndroidEntryPoint
class GameContentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameContentActvityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameContentActvityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.gameWebView.loadUrl(AppModule.gameURL)
        if(AppModule.status == "success")
        {
            val adRequest = AdRequest.Builder().build()
            binding.adView.loadAd(adRequest)
        }
        else
            binding.adView.visibility = View.GONE
    }

    companion object {
        fun getStartIntent(context: Context) = Intent(context, GameContentActivity::class.java)
    }
}