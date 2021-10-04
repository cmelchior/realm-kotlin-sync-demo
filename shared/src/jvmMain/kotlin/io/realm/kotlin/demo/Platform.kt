package io.realm.kotlin.demo

actual class Platform actual constructor() {
    actual val platform: String = "JVM (${System.getProperty("os.name")})"
}