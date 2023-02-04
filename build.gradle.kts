plugins {

    id(PluginId.Android.application) version (Version.androidPlugin) apply (false)
    id(PluginId.Android.library) version (Version.androidPlugin) apply (false)
    id(PluginId.Kotlin.kotlinAndroid) version (Version.kotlin) apply (false)
    id(PluginId.Hilt.hilt) version (Version.hilt) apply (false)
    id(PluginId.Google.googleService) version (Version.googleService)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}