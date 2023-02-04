object Version {
    const val core = "1.8.0"
    const val appcompat = "1.5.1"
    const val material = "1.8.0-alpha01"
    const val constraintLayout = "2.1.4"
    const val navigation = "2.5.2"

    const val junit = "4.13.2"
    const val androidJunit = "1.1.3"
    const val espresso = "3.4.0"

    const val hilt = "2.44"
    const val hiltNavFrag = "1.0.0"

    const val retrofit = "2.9.0"
    const val okhttp = "4.10.0"

    const val activityKtx = "1.5.1"
    const val fragmentKtx = "1.5.4"

    const val glide = "4.13.2"

    const val google = "20.3.0"
    const val googleService = "4.3.14"
    const val indicator = "2.1.6"
    const val blur = "4.0.1"

    const val room = "2.4.3"
    const val common = "2.4"

    const val firebase = "31.1.0"

    const val androidPlugin = "7.2.1"
    const val kotlin = "1.7.10"
}

object Project {
    const val data = ":data"
    const val domain = ":domain"
    const val presentation = ":presentation"
    const val common = ":common"
}

object PluginId {
    object Android {
        const val application = "com.android.application"
        const val library = "com.android.library"
    }

    object Kotlin {
        const val kotlinAndroid = "org.jetbrains.kotlin.android"
        const val kotlinKapt = "kotlin-kapt"
    }

    object Google {
        const val googleService = "com.google.gms.google-services"
    }

    object Hilt {
        const val hilt = "com.google.dagger.hilt.android"
    }
}

object Dependencies {
    object Androidx {
        const val core = "androidx.core:core-ktx:${Version.core}"
        const val junit = "androidx.test.ext:junit:${Version.androidJunit}"
        const val espresso = "androidx.test.espresso:espresso-core:${Version.espresso}"
        const val appcompat = "androidx.appcompat:appcompat:${Version.appcompat}"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Version.constraintLayout}"

        const val navFragmentKtx =
            "androidx.navigation:navigation-fragment-ktx:${Version.navigation}"
        const val navUiKtx = "androidx.navigation:navigation-ui-ktx:${Version.navigation}"

        const val activityKtx = "androidx.activity:activity-ktx:${Version.activityKtx}"
        const val fragmentKtx = "androidx.fragment:fragment-ktx:${Version.fragmentKtx}"
    }

    object Hilt {
        const val hiltAndroid = "com.google.dagger:hilt-android:${Version.hilt}"
        const val hiltNavFragment = "androidx.hilt:hilt-navigation-fragment:${Version.hiltNavFrag}"
        const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:${Version.hilt}"
    }

    object Network {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofit}"
        const val retrofitGson = "com.squareup.retrofit2:converter-gson:${Version.retrofit}"
        const val okhttp = "com.squareup.okhttp3:okhttp:${Version.okhttp}"
        const val okhttpLogging = "com.squareup.okhttp3:logging-interceptor:${Version.okhttp}"
    }

    object Glide {
        const val glide = "com.github.bumptech.glide:glide:${Version.glide}"
        const val glideCompiler = "com.github.bumptech.glide:compiler:${Version.glide}"
    }

    object Room {
        const val roomDB = "androidx.room:room-runtime:${Version.room}"
        const val roomKtx = "androidx.room:room-ktx:${Version.room}"
        const val roomCompiler = "androidx.room:room-compiler:${Version.room}"
    }

    object Test {
        const val junit = "junit:junit:${Version.junit}"
    }

    object Material {
        const val material = "com.google.android.material:material:${Version.material}"
    }

    object Google {
        const val gmsService = "com.google.android.gms:play-services-auth:${Version.google}"
        const val firebaseAnalytics = "com.google.firebase:firebase-analytics-ktx"
        const val firebaseBom = "com.google.firebase:firebase-bom:${Version.firebase}"
    }

    object Common {
        const val common = "commons-io:commons-io:${Version.common}"
    }

    object View {
        const val circleIndicator = "me.relex:circleindicator:${Version.indicator}"
        const val blur = "jp.wasabeef:blurry:${Version.blur}"
    }
}