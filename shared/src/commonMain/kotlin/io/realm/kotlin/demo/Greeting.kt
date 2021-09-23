package io.realm.kotlin.demo

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}