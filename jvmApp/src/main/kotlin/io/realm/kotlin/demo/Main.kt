package io.realm.kotlin.demo

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import io.realm.kotlin.demo.theme.RealmColor
import io.realm.kotlin.demo.shared.feature.counter.SharedCounterViewModel

fun main() {
    val vm = SharedCounterViewModel()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "Realm Kotlin Demo - ${vm.platform}",
            state = rememberWindowState(width = 320.dp, height = 500.dp)
        ) {
            MaterialTheme {
                Surface(color = RealmColor.GrapeJelly) {
                    Column {
                        CounterButton(Modifier.weight(1F)) {
                            vm.increment()
                        }
                        CounterButton(Modifier.weight(1F)) {
                            vm.decrement()
                        }
                    }
                    Box(Modifier.fillMaxSize()) {
                        val state: String by vm.observeCounter()
                            .collectAsState(initial = "-")
                        Text(
                            text = state,
                            modifier = Modifier.align(Alignment.Center),
                            style = MaterialTheme.typography.h1,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun CounterButton(modifier: Modifier = Modifier, action: () -> Unit) {
    Box(modifier = modifier
        .fillMaxWidth()
        .clickable {
            action()
        }
    )
}