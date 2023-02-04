plugins {
    id(PluginId.Android.library)
    id(PluginId.Kotlin.kotlinAndroid)
    id(PluginId.Kotlin.kotlinKapt)
    id(PluginId.Hilt.hilt)
}

android {
    namespace = Config.applicationId + ".common"
    compileSdk = Config.compileSdk

    defaultConfig {
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk

        testInstrumentationRunner = Config.testInstrumentationRunner
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {

    //Hilt
    implementation(Dependencies.Hilt.hiltAndroid)
    kapt(Dependencies.Hilt.hiltAndroidCompiler)

    //File Util
    implementation(Dependencies.Common.common)
}