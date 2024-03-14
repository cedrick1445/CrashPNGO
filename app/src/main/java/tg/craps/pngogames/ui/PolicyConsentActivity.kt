package tg.craps.pngogames.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.facebook.FacebookSdk
import tg.craps.pngogames.di.AppModule
import tg.craps.pngogames.util.AppPermission
import tg.craps.pngogames.util.PersistenceHelper
import dagger.hilt.android.AndroidEntryPoint
import tg.craps.pngogames.databinding.ActivityPolicyConsentBinding
import javax.inject.Inject

@AndroidEntryPoint
class PolicyConsentActivity : AppCompatActivity() {

    @Inject
    lateinit var permission: AppPermission

    @Inject
    lateinit var persistence: PersistenceHelper

    private lateinit var binding: ActivityPolicyConsentBinding

    private val permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){
        if(permission.isGranted){
            showGameContent()
        }
    }

    private fun showGameContent() {
        persistence.setUserResponseWithPolicyConsent(true)
        startActivity(GameContentActivity.getStartIntent(this))
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPolicyConsentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
    }

    private fun setupView() {
        with(binding){

            policyWebView.loadUrl(AppModule.policyURL)
            agreeButton.setOnClickListener {
                showConfirmDialog()
            }
            disagreeButton.setOnClickListener {
                finish()
            }
        }
    }

    private fun showConfirmDialog() {
        AlertDialog.Builder(this)
            .setTitle("User Data Consent")
            .setMessage("We need your permission to send data to  activity for analytics and provide better  user experience")
            .setPositiveButton("Accept"){ d, _ ->
                FacebookSdk.setAutoInitEnabled(true)
                FacebookSdk.fullyInitialize()
                FacebookSdk.setAutoLogAppEventsEnabled(true);
                FacebookSdk.setAdvertiserIDCollectionEnabled(true);
                checkRequiredPermission()
                d.dismiss()
            }.setNegativeButton("Disagree"){ d, _ ->
                FacebookSdk.setAutoInitEnabled(false)
                FacebookSdk.fullyInitialize()
                FacebookSdk.setAutoLogAppEventsEnabled(false);
                FacebookSdk.setAdvertiserIDCollectionEnabled(false);
                d.dismiss()
            }.create()
            .show()
    }

    private fun checkRequiredPermission() {
        if(permission.isGranted.not()){
            permission.requestPermission(permissionLauncher)
        } else {
            showGameContent()
        }
    }

    companion object {
        fun getStartIntent(context: Context) = Intent(context, PolicyConsentActivity::class.java)
    }
}