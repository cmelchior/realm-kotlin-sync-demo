//
//  ViewModel.swift
//  iosApp
//
//  Created by Christian Melchior on 28/09/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import Combine
import shared

class CounterViewModel: ObservableObject {
    @Published var counter: String = "-"

    private let repository: CounterRepository
    private var job: Closeable? = nil
        
    init(repository: CounterRepository) {
        self.repository = repository
    }
    
    func increment() {
        self.repository.adjust(change: 1)
    }

    func decrement() {
        self.repository.adjust(change: -1)
    }
    
    func startObservingCounter() {
        self.job = self.repository.observeCommon().watch { counterValue in
            self.counter = counterValue!.stringValue
        }
    }
    
    func stopObservingCounter() {
        job?.close()
    }
}
