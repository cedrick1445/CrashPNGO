plugins {
    id("com.android.application")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("com.google.gms.google-services")
    id("kotlin-android")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "tg.craps.pngogames"
    compileSdk = 33

    defaultConfig {
        applicationId = "tg.craps.pngogames"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //Volley
    implementation("com.android.volley:volley:1.2.1")

    //Firebase
    implementation(platform("com.google.firebase:firebase-bom:32.3.1"))
    implementation("com.google.firebase:firebase-config:21.5.0")

    implementation("com.android.installreferrer:installreferrer:2.2")

    //Hilt
    implementation("com.google.dagger:hilt-android:2.44")
    kapt("com.google.dagger:hilt-android-compiler:2.44")
    //GSON
    implementation("com.google.code.gson:gson:2.8.9")

    //AdMob
    implementation("com.google.android.gms:play-services-ads-identifier:18.0.1")
    implementation("com.google.android.gms:play-services-appset:16.0.2")
    implementation("com.google.android.gms:play-services-ads:22.4.0")

    //Facebook
    implementation("com.facebook.android:facebook-android-sdk:[8,9)")
    implementation("com.facebook.android:facebook-android-sdk:16.2.0")
}

kapt {
    correctErrorTypes = true
}