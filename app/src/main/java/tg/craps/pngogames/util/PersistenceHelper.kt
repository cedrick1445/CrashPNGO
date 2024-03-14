package tg.craps.pngogames.util

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PersistenceHelper @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private val sharedPref: SharedPreferences by lazy {
        context.getSharedPreferences(context.packageName, MODE_PRIVATE)
    }

    private val editor: SharedPreferences.Editor by lazy {
        sharedPref.edit()
    }

    companion object {
        const val POLICY_CONSENT_RESPONSE_KEY = "POLICY_CONSENT_RESPONSE_KEY"
    }

    val isUserAgreedWithPolicyConsent: Boolean
        get() = sharedPref.getBoolean(POLICY_CONSENT_RESPONSE_KEY, false)

    fun setUserResponseWithPolicyConsent(agrees: Boolean) {
        editor.putBoolean(POLICY_CONSENT_RESPONSE_KEY, agrees).apply()
    }

}