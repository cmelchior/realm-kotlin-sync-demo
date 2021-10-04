package io.realm.kotlin

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}