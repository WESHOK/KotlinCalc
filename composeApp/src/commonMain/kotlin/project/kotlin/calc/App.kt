package project.kotlin.calc

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
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        var input by remember { mutableStateOf("") }
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Column(modifier = Modifier.padding(16.dp)) {
                OutlinedTextField(
                    value = input,
                    onValueChange = {
                        fun isArithmeticOperator(c: Char): Boolean {
                            val operators = setOf('/', '*', '+', '-')
                            return c in operators
                        }
                        if (it.all { char -> char.isDigit() or isArithmeticOperator(char) }) {
                            input = it
                        }
                    },
                    label = { Text("Вводите числа") },
                    modifier = Modifier.fillMaxWidth()
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
                Button(onClick = { input += '=' }) {
                    Text("=")
                }
            }
        }
    }
}