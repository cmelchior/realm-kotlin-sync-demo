package io.realm.kotlin

actual class Platform actual constructor() {
    actual val platform: String = "JVM ${System.getProperty("os.name")}"
}