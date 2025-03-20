package project.kotlin.calc

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        var input by remember { mutableStateOf("") }
        var count by remember { mutableStateOf(0) }

        fun Char.isArithmetic(): Boolean {
            return this in setOf('/', '*', '+', '-')
        }

        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Row(modifier = Modifier.padding(16.dp)) {
                OutlinedTextField(
                    value = input,
                    onValueChange = {
                        if (it.all { char -> char.isDigit() or char.isArithmetic() }) {
                            input = it
                        }
                    },
                    label = { Text("Вводите числа") },
                    modifier = Modifier.width(200.dp)
                )
                Text(
                    "Result: $count",
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(16.dp)
                )
            }
            Row {
                Button(onClick = { input += '+' }) {
                    Text("+")
                }
                Button(onClick = { input += '-' }) {
                    Text("-")
                }
                Button(onClick = { input += '*' }) {
                    Text("*")
                }
                Button(onClick = { input += '/' }) {
                    Text("/")
                }
                Button(onClick = {
                    println(input)
                    val digits = input.split("[*/+-]".toRegex(), limit = 2).map { it.toInt() }
                    when (input.filter { it.isArithmetic() }) {
                        "+" -> count = digits[0] + digits[1]
                        "-" -> count = digits[0] - digits[1]
                        "*" -> count = digits[0] * digits[1]
                        "/" -> count = digits[0] / digits[1]
                    }
                    input = ""
                }) {
                    Text("=")
                }
            }
        }
    }
}