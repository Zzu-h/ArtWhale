import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id(PluginId.Android.library)
    id(PluginId.Kotlin.kotlinAndroid)
    id(PluginId.Kotlin.kotlinKapt)
    id(PluginId.Hilt.hilt)
}

android {
    namespace = Config.applicationId + ".data"
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

    val baseUrl: String = gradleLocalProperties(rootDir).getProperty("BASE_URL")

    defaultConfig {
        buildConfigField("String", "BASE_URL", baseUrl)
    }
}

dependencies {

    implementation(project(Project.common))

    //Test
    testImplementation(Dependencies.Test.junit)
    androidTestImplementation(Dependencies.Androidx.junit)
    androidTestImplementation(Dependencies.Androidx.espresso)

    //Hilt
    implementation(Dependencies.Hilt.hiltAndroid)
    kapt(Dependencies.Hilt.hiltAndroidCompiler)

    //Retrofit2
    implementation(Dependencies.Network.retrofit)
    implementation(Dependencies.Network.retrofitGson)

    //okhttp3 Dependency
    implementation(Dependencies.Network.okhttp)
    implementation(Dependencies.Network.okhttpLogging)

    //RoomDB
    implementation(Dependencies.Room.roomDB)
    implementation(Dependencies.Room.roomKtx)
    kapt(Dependencies.Room.roomCompiler)
}