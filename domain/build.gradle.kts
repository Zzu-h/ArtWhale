plugins {
    id(PluginId.Android.library)
    id(PluginId.Kotlin.kotlinAndroid)
    id(PluginId.Kotlin.kotlinKapt)
    id(PluginId.Hilt.hilt)
}

android {
    namespace = Config.applicationId + ".domain"
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

    implementation(project(Project.data))
    implementation(project(Project.common))

    //Hilt
    implementation(Dependencies.Hilt.hiltAndroid)
    kapt(Dependencies.Hilt.hiltAndroidCompiler)
}