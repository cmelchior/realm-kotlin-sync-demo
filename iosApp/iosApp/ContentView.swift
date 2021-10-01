//
//  ContentView.swift
//  macosApp
//
//  Created by Christian Melchior on 24/09/2021.
//

import SwiftUI
import shared

struct ContentView: View {
    @ObservedObject var vm = CounterViewModel(repository: CounterRepository())
    let screen = UIScreen.main.bounds
    var body: some View {
        ZStack {
            Color.white
                .frame(
                    minWidth: screen.width,
                    maxWidth: .infinity,
                    minHeight: screen.height,
                    maxHeight: .infinity
                )

            VStack(spacing: 0) {
                Button {
                    vm.increment()
                } label: {
                    RealmColor.indigo
                        .frame(
                            minWidth: screen.width,
                            maxWidth: .infinity,
                            minHeight: screen.height,
                            maxHeight: .infinity
                        )
                }
                .buttonStyle(PlainButtonStyle())
                .frame(
                    minWidth: screen.width,
                    minHeight: screen.height,
                    alignment: .center
                )
                Button {
                    vm.decrement()
                } label: {
                    RealmColor.indigo
                        .frame(
                            minWidth: screen.width,
                            maxWidth: .infinity,
                            minHeight: screen.height,
                            maxHeight: .infinity
                        )
                }
                .buttonStyle(PlainButtonStyle())
                .frame(
                    minWidth: screen.width,
                    minHeight: screen.height,
                    alignment: .center
                )
            }
            .frame(
                minWidth: screen.width,
                minHeight: screen.height
            )
            
            Text(vm.counter)
                .fontWeight(.bold)
                .font(.system(size: 150))

        }
        .onAppear {
            vm.startObservingCounter()
        }
        .onDisappear {
            vm.stopObservingCounter()
        }
    }}

struct ContentView_Previews: PreviewProvider {
   static var previews: some View {
       ContentView()
           .previewDevice(PreviewDevice(rawValue: "Mac"))
   }
}
