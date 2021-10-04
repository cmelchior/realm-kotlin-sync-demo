package io.realm.kotlin.shared.viewmodel.counter

import io.realm.kotlin.shared.data.CounterRepository
import io.realm.kotlin.shared.util.CommonFlow
import io.realm.kotlin.shared.util.asCommonFlow
import kotlinx.coroutines.flow.map

/**
 * Class for the shared parts of the ViewModel.
 *
 * ViewModels are split into two parts:
 * - `SharedViewModel`, which contains the business logic and communication with
 *   the repository / model layer.
 * - `PlatformViewModel`, which is only a thin wrapper for hooking the SharedViewModel
 *   up to either SwiftUI (through `@ObservedObject`) or to Compose (though Flows).
 *
 * The boundary between these two classes must only be [CommonFlow]'s, which emit
 * on the UI or Main thread.
 *
 * This allows the UI to be fully tested by injecting a mocked ViewModel on the
 * platform side.
 */
class SharedCounterViewModel: CounterViewModel {

    // Implementation note: With a ViewModel this simple, just merging it with
    // Repository would probably be simpler, but by splitting the Repository
    // and ViewModel, we only need to enforce CommonFlows at the boundary, and
    // it means the CounterViewModel can be mocked easily in the View Layer.
    private val repository = CounterRepository()

    override fun observeCounter(): CommonFlow<String> {
        return repository.observeCounter()
            .map { count -> count.toString() }
            .asCommonFlow()
    }

    override fun increment() {
        repository.adjust(1)
    }

    override fun decrement() {
        repository.adjust(-1)
    }
}
