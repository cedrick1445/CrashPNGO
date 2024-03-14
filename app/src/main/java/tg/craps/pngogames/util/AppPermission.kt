package tg.craps.pngogames.util

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.result.ActivityResultLauncher
import androidx.core.content.ContextCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AppPermission @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val permissions: Array<String>
        get() = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            if(Build.VERSION.SDK_INT == Build.VERSION_CODES.TIRAMISU){
                Manifest.permission.READ_MEDIA_IMAGES
            } else {
                Manifest.permission.READ_EXTERNAL_STORAGE
            }
        )
    val isGranted: Boolean
        get() = permissions.all { permission -> ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED}

    fun requestPermission(launcher: ActivityResultLauncher<Array<String>>) {
        launcher.launch(permissions)
    }
}