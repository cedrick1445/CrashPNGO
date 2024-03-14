package tg.craps.pngogames.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.webkit.JavascriptInterface
import android.widget.Toast
import dagger.hilt.android.qualifiers.ApplicationContext
import org.json.JSONException
import org.json.JSONObject
import javax.inject.Inject


class JScript @Inject constructor(
    @ApplicationContext private val context: Context
) {

    @JavascriptInterface
    fun postMessage(name: String, data: String){
        val eventValue: MutableMap<String, Any> = HashMap()
        if ("openWindow" == name) {
            try {
                val extLink = JSONObject()
                val newWindow = Intent(Intent.ACTION_VIEW)
                newWindow.data = Uri.parse(extLink.getString("url"))
                newWindow.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(newWindow)
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        } else {
            eventValue[name] = data
        }
    }
}