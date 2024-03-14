
-keepattributes JavascriptInterface
-keepattributes Signature
-keepattributes SetJavaScriptEnabled
-keepclassmembers class * {
   @android.webkit.JavascriptInterface
    <methods>;
}

# Keep the Google Play Install Referrer classes
-keep class com.google.android.gms.common.ConnectionResult {
   int SUCCESS;
}

-assumenosideeffects class android.util.Log {
    public static boolean isLoggable(java.lang.String, int);
    public static int v(...);
    public static int d(...);
    public static int i(...);
    public static int w(...);
    public static int e(...);
}