buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven("https://oss.sonatype.org/content/repositories/snapshots")
        mavenLocal()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.21")
        classpath("com.android.tools.build:gradle:7.0.2")
        classpath("io.realm.kotlin:gradle-plugin:0.6.0-SYNC-SNAPSHOT")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://oss.sonatype.org/content/repositories/snapshots")
        mavenLocal()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}