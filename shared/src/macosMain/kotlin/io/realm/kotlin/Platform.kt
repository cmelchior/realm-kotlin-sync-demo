package io.realm.kotlin

import platform.Foundation.NSHost

actual class Platform actual constructor() {
    actual val platform: String = NSHost.description() ?: "Unknown"
}