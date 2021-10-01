package io.realm.kotlin.demo.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.tooling.preview.Preview
import io.realm.kotlin.demo.theme.MyApplicationTheme
import io.realm.kotlin.demo.theme.RealmColor

class CounterActivity : ComponentActivity() {

    private val viewModel = CounterViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CounterApp(viewModel)
        }
    }

    @Composable
    fun CounterApp(vm: CounterViewModel) {
        MyApplicationTheme {
            Surface(color = RealmColor.SexySalmon) {
                Column {
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.5F)
                        .clickable {
                            vm.increment()
                        }
                    )
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(1F)
                        .clickable {
                            vm.decrement()
                        }
                    )
                }
                Box(Modifier.fillMaxSize()) {
                    val value: String by vm.observe().collectAsState(initial = "-")
                    Text(
                        text = value,
                        modifier = Modifier.align(Alignment.Center),
                        style = MaterialTheme.typography.h1,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        CounterApp(viewModel)
    }
}