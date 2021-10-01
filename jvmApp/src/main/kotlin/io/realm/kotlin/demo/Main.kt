package io.realm.kotlin.demo

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.State
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
import kotlinx.coroutines.flow.map

fun main() {
    val repository = CounterRepository()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "Realm Kotlin Sync Demo",
            state = rememberWindowState(width = 325.dp, height = 500.dp)
        ) {
            MaterialTheme {
                Surface(color = RealmColor.GrapeJelly) {
                    Column {
                        Box(modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.5F)
                            .clickable {
                                repository.adjust(1)
                            }
                        )
                        Box(modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(1F)
                            .clickable {
                                repository.adjust(-1)
                            }
                        )
                    }
                    Box(Modifier.fillMaxSize()) {
                        val state: String by repository.observe()
                            .map { it.toString() }
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