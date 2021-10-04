package io.realm.kotlin.shared.viewmodel.counter

import io.realm.kotlin.shared.util.CommonFlow
import io.realm.kotlin.shared.viewmodel.SharedViewModel

/**
 * Interface describing the ViewModel on both the `shared` and `platform` side.
 */
interface CounterViewModel: SharedViewModel {
    fun observeCounter(): CommonFlow<String>
    fun increment()
    fun decrement()
}