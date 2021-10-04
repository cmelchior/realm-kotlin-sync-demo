package io.realm.kotlin.demo.shared.feature.counter

import io.realm.kotlin.demo.Platform
import io.realm.kotlin.demo.shared.feature.SharedViewModel
import io.realm.kotlin.demo.shared.util.CommonFlow

/**
 * Interface describing the ViewModel on both the `shared` and `platform` side.
 */
interface CounterViewModel: SharedViewModel {
    val platform: String
        get() = Platform().platform
    fun observeCounter(): CommonFlow<String>
    fun increment()
    fun decrement()
}