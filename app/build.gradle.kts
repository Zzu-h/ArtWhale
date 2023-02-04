plugins {
    id(PluginId.Android.application)
    id(PluginId.Kotlin.kotlinAndroid)
    id(PluginId.Kotlin.kotlinKapt)
    id(PluginId.Hilt.hilt)
    id(PluginId.Google.googleService)
}

android {
    compileSdk = Config.compileSdk

    defaultConfig {
        applicationId = Config.applicationId
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk
        versionCode = Config.versionCode
        versionName = Config.versionName

        testInstrumentationRunner = Config.testInstrumentationRunner
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
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
    kotlinOptions {
        jvmTarget = Config.jvmTarget
    }
    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
}

dependencies {

    implementation(project(Project.domain))

    implementation(Dependencies.Androidx.core)
    implementation(Dependencies.Androidx.appcompat)
    implementation(Dependencies.Material.material)
    implementation(Dependencies.Androidx.constraintLayout)
    implementation(Dependencies.Androidx.navFragmentKtx)
    implementation(Dependencies.Androidx.navUiKtx)

    testImplementation(Dependencies.Test.junit)
    androidTestImplementation(Dependencies.Androidx.junit)
    androidTestImplementation(Dependencies.Androidx.espresso)

    //Hilt Dependency
    implementation(Dependencies.Hilt.hiltAndroid)
    implementation(Dependencies.Hilt.hiltNavFragment)
    kapt(Dependencies.Hilt.hiltAndroidCompiler)

    //Retrofit2 Dependency
    implementation(Dependencies.Network.retrofit)
    implementation(Dependencies.Network.retrofitGson)

    //okhttp3 Dependency
    implementation(Dependencies.Network.okhttp)
    implementation(Dependencies.Network.okhttpLogging)

    //Glide Dependency
    implementation(Dependencies.Glide.glide)
    kapt(Dependencies.Glide.glideCompiler)

    //viewModels Dependency
    implementation(Dependencies.Androidx.activityKtx)
    implementation(Dependencies.Androidx.fragmentKtx)

    //circle Indicator
    implementation(Dependencies.View.circleIndicator)

    //blur view
    implementation(Dependencies.View.blur)

    //RoomDB
    implementation(Dependencies.Room.roomDB)
    implementation(Dependencies.Room.roomKtx)
    kapt(Dependencies.Room.roomCompiler)

    //File Util
    implementation(Dependencies.Common.common)

    //google Dependency
    implementation(Dependencies.Google.gmsService)

    //firebase
    implementation(Dependencies.Google.firebaseAnalytics)
    implementation(platform(Dependencies.Google.firebaseBom))
}