package project.kotlin.calc

import kotlin.math.pow
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
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.input.key.*
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        var input by remember { mutableStateOf("") }
        var count by remember { mutableStateOf("") }

        fun Char.isArithmetic(): Boolean {
            return this in setOf('/', '*', '+', '-', '^')
        }

        fun calculate() {
            val digits = input.split("[*/+^-]".toRegex(), limit = 2).map { it.toDouble() }

            when (input.filter { it.isArithmetic() }) {
                "+" -> count = try { (digits[0] + digits[1]).toString().removeSuffix(".0") } catch (e: ArithmeticException) { "NaN" }
                "-" -> count = try { (digits[0] - digits[1]).toString().removeSuffix(".0") } catch (e: ArithmeticException) { "NaN" }
                "*" -> count = try { (digits[0] * digits[1]).toString().removeSuffix(".0") } catch (e: ArithmeticException) { "NaN" }
                "/" -> count = try { (digits[0] / digits[1]).toString().removeSuffix(".0") } catch (e: ArithmeticException) { "NaN" }
                "^" -> count = try { digits[0].pow(digits[1]).toString().removeSuffix(".0") } catch (e: ArithmeticException) { "NaN" }
            }
            input = ""
        }

        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Row(modifier = Modifier.padding(16.dp)) {
                OutlinedTextField(
                    value = input,
                    onValueChange = {
                        if (it.all { char -> char.isDigit() or char.isArithmetic() or (char in setOf(',', '.')) }) {
                            input = it.replace(',', '.')
                        }
                    },
                    label = { Text("Введите числа") },
                    modifier = Modifier
                        .onPreviewKeyEvent { if (it.key == Key.Enter && it.type == KeyEventType.KeyDown) {
                            calculate()
                            true
                        } else false }
                        .width(200.dp)
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
                Button(onClick = { input += '^' }) {
                    Text("^")
                }
                Button(onClick = { calculate() } ) {
                    Text("=")
                }
            }
            Row {
                Button(onClick = { input += '7' }) {
                    Text("7")
                }
                Button(onClick = { input += '8' }) {
                    Text("8")
                }
                Button(onClick = { input += '9' }) {
                    Text("9")
                }
            }
            Row {
                Button(onClick = { input += '6' }) {
                    Text("6")
                }
                Button(onClick = { input += '5' }) {
                    Text("5")
                }
                Button(onClick = { input += '4' }) {
                    Text("4")
                }
            }
            Row {
                Button(onClick = { input += '3' }) {
                    Text("3")
                }
                Button(onClick = { input += '2' }) {
                    Text("2")
                }
                Button(onClick = { input += '1' }) {
                    Text("1")
                }
            }
            Row {
                Button(onClick = { input += '0' }) {
                    Text("0")
                }
                Button(onClick = { input += '.' }) {
                    Text(".")
                }
                Button(onClick = { input = "" }) {
                    Text("C")
                }
            }
        }
    }
}