package io.realm.kotlin.demo

import platform.Foundation.NSHost

actual class Platform actual constructor() {
    actual val platform: String = NSHost.description() ?: "Unknown"
}