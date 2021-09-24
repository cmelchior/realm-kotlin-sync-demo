pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven(url = "https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

rootProject.name = "Realm_Kotlin_Sync_Demo"
include(":androidApp")
include(":jvmApp")
include(":shared")