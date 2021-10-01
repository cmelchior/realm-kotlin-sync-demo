package io.realm.kotlin.demo.ui

import androidx.lifecycle.ViewModel
import io.realm.kotlin.demo.CounterRepository
import kotlinx.coroutines.flow.*

class CounterViewModel: ViewModel() {

    private val repository = CounterRepository()

    fun observe(): Flow<String> = repository.observe().map { it.toString() }

    fun increment() {
        repository.adjust(1)
    }

    fun decrement() {
        repository.adjust(-1)
    }
}