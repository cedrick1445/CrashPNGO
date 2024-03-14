package tg.craps.pngogames.di

import android.app.Application
import com.facebook.FacebookSdk
import com.facebook.LoggingBehavior
import com.facebook.appevents.AppEventsLogger
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication: Application(){
    override fun onCreate() {
        super.onCreate()
        FacebookSdk.fullyInitialize();
        AppEventsLogger.activateApp(this);
        FacebookSdk.setIsDebugEnabled(true);
        FacebookSdk.addLoggingBehavior(LoggingBehavior.APP_EVENTS);
    }
}
