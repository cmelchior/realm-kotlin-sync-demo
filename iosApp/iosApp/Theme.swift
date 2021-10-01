//
//  Theme.swift
//  macosApp
//
//  Created by Christian Melchior on 01/10/2021.
//

import Foundation
import SwiftUI

// Credit: https://stackoverflow.com/a/56874327/1389357
extension Color {
    init(hex: UInt, alpha: Double = 1) {
        self.init(
            .sRGB,
            red: Double((hex >> 16) & 0xff) / 255,
            green: Double((hex >> 08) & 0xff) / 255,
            blue: Double((hex >> 00) & 0xff) / 255,
            opacity: alpha
        )
    }
}

struct RealmColor {
    // Greys
    static let charcoal = Color.init(hex: 0x1C233F)
    static let elephant = Color.init(hex: 0x9A9BA5)
    static let dov = Color.init(hex: 0xEBEBF2)

    // Orb colors
    static let ultramarine = Color.init(hex: 0x39477F)
    static let indigo = Color.init(hex: 0x59569E)
//    static let ultramarine = Color.init(hex: 0x39477F)
//    static let ultramarine = Color.init(hex: 0x39477F)

}

//object RealmColor {
//    // Grays
//    val Charcoal = Color(0xFF1C233F)
//    val Elephant = Color(0xFF9A9BA5)
//    val Dov = Color(0xFFEBEBF2)
//
//    // Orb colors
//    val Ultramarine = Color(0xFF39477F)
//    val Indigo = Color(0xFF59569E)
//    val GrapeJelly = Color(0xFF9A59A5)
//    val Mulberry = Color(0xFFD34CA3)
//    val Flamingo = Color(0xFFF25192)
//    val SexySalmon = Color(0xFFF77C88)
//    val Peach = Color(0xFFFC9F95)
//    val Melon = Color(0xFFFCC397)
//}
