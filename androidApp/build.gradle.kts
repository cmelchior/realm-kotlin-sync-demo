plugins {
    id("com.android.application")
    kotlin("android")
}

val compose_version = "1.0.2"

dependencies {
    implementation(project(":shared"))

//    compileOnly("io.realm.kotlin:library:0.4.1")
    implementation("androidx.compose.compiler:compiler:$compose_version")
    implementation("androidx.compose.material:material:$compose_version")
    implementation("androidx.compose.ui:ui:$compose_version")
    implementation("androidx.compose.ui:ui-tooling:$compose_version")
    implementation("androidx.activity:activity-compose:1.4.0-alpha02")

//    implementation("androidx.appcompat:appcompat:1.3.1")

//    implementation("androidx.core:core-ktx:1.6.0")
//    implementation("androidx.appcompat:appcompat:1.3.0")
//    implementation("com.google.android.material:material:1.3.0")
//    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
//    implementation("androidx.navigation:navigation-runtime-ktx:2.3.5")
//    implementation("androidx.navigation:navigation-compose:2.4.0-alpha01")
//    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:2.3.1")
//    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:1.0.0-alpha05")



//    implementation("com.google.android.material:material:1.4.0")
//    implementation("androidx.constraintlayout:constraintlayout:2.1.0")

}

android {
    compileSdk = 31
    defaultConfig {
        applicationId = "io.realm.kotlin.demo"
        minSdk = 21
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    // Required by Compose
    kotlinOptions {
        jvmTarget = "11"
        freeCompilerArgs += "-Xallow-jvm-ir-dependencies"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
//        kotlinCompilerVersion = "1.5.21"
        kotlinCompilerExtensionVersion = compose_version
    }
}