package tg.craps.pngogames.ui

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import tg.craps.pngogames.di.AppModule
import tg.craps.pngogames.util.AppPermission
import tg.craps.pngogames.util.JScript
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@SuppressLint("SetJavaScriptEnabled")
@AndroidEntryPoint
class WebView: WebView {
    constructor(context: Context): super(context)
    constructor(context: Context, attributeSet: AttributeSet): super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int): super(context, attributeSet, defStyleAttr)

    @Inject
    lateinit var permissions: AppPermission

    @Inject
    lateinit var jScript: JScript

    init {
        if (isInEditMode.not()) {
            settings.apply {
                javaScriptEnabled = true
                domStorageEnabled = true
                loadsImagesAutomatically = true
                allowFileAccess = true
                setSupportMultipleWindows(true)
                cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
                mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
                javaScriptCanOpenWindowsAutomatically = true
            }
            webViewClient = WebViewClient()
            if(permissions.isGranted) addJavascriptInterface(jScript, AppModule.jsInterface)
        }
    }
}