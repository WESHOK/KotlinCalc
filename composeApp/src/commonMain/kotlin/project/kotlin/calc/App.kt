package project.kotlin.calc

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlincalc.composeapp.generated.resources.Res
import kotlincalc.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        val showContent by remember { mutableStateOf(false) }
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Column(modifier = Modifier.padding(16.dp)) {
                var number by remember { mutableStateOf("") }

                OutlinedTextField(
                    value = number,
                    onValueChange = {
                        if (it.all { char -> char.isDigit() }) {
                            number = it
                        }
                    },
                    label = { Text("Вводите числа") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Row {
                Button(onClick = { println("thats a plus!") }) {
                    Text("+")
                }
                Button(onClick = { println("thats a minus!") }) {
                    Text("-")
                }
                Button(onClick = { println("thats a multiply!") }) {
                    Text("*")
                }
                Button(onClick = { println("thats a divide!") }) {
                    Text("/")
                }
                Button(onClick = { println("thats a equality button!") }) {
                    Text("=")
                }
            }
            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text("Compose: $greeting")
                }
            }
        }
    }
}