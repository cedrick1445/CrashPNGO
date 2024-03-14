package tg.craps.pngogames.di

import android.content.Context
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    var appCode = "TG12101"
    var apiURL = ""
    var gameURL = ""
    var policyURL = ""
    var jsInterface = ""
    var status = ""

    @Provides
    @Singleton
    fun providesFirebaseRemoteConfig(): FirebaseRemoteConfig =
        FirebaseRemoteConfig.getInstance().apply {
            setConfigSettingsAsync(
                FirebaseRemoteConfigSettings.Builder()
                    .setMinimumFetchIntervalInSeconds(3600)
                    .build()
            )
        }

    @Provides
    @Singleton
    fun providesRequestQueue(
        @ApplicationContext context: Context
    ): RequestQueue = Volley.newRequestQueue(context)

}