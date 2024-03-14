package tg.craps.pngogames.ui

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import tg.craps.pngogames.di.AppModule
import tg.craps.pngogames.util.PersistenceHelper
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.google.firebase.remoteconfig.ConfigUpdate
import com.google.firebase.remoteconfig.ConfigUpdateListener
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigException
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONException
import org.json.JSONObject
import tg.craps.pngogames.R
import javax.inject.Inject

@AndroidEntryPoint
class LoadingActivity : AppCompatActivity() {

    @Inject
    lateinit var remoteConfig: FirebaseRemoteConfig

    @Inject
    lateinit var apiConnection: RequestQueue

    @Inject
    lateinit var persistence: PersistenceHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)
        window.attributes.flags = (WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        initViews()
    }

    private fun initViews() {
        remoteConfig.fetchAndActivate().addOnCompleteListener {
            setAppConfigs()
        }

        remoteConfig.addOnConfigUpdateListener(object : ConfigUpdateListener {
            override fun onUpdate(configUpdate: ConfigUpdate) {
                setAppConfigs()
            }

            override fun onError(error: FirebaseRemoteConfigException) {
                setAppConfigs()
            }
        })
    }

    private fun setAppConfigs() {
        AppModule.apiURL = remoteConfig.getString("apiURL")
        AppModule.policyURL = remoteConfig.getString("policyURL")
        AppModule.jsInterface = remoteConfig.getString("jsInterface")
        loadGameContent()
    }

    private fun loadGameContent() {
        val requestBody = JSONObject().put("appid", AppModule.appCode)
        val endpoint = AppModule.apiURL + "?appid=" + AppModule.appCode
        val request = JsonObjectRequest(Request.Method.GET, endpoint, requestBody, {
            try {
                AppModule.gameURL = it.getString("gameURL")
                AppModule.status = it.getString("status")
            } catch (e: JSONException) {
                e.printStackTrace()
            }
            if (persistence.isUserAgreedWithPolicyConsent) {
                startActivity(GameContentActivity.getStartIntent(this))
            } else {
                startActivity(PolicyConsentActivity.getStartIntent(this))
            }
            finish()
        }) {
            it.printStackTrace()
        }
        apiConnection.add(request)
    }
}