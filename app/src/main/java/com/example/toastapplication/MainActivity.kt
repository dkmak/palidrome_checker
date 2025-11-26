package com.example.ispalindrome

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.ispalindrome.ui.theme.IsPalindromeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var input: String by remember { mutableStateOf("") }
            var isPalindrome: Boolean? by remember { mutableStateOf(null) }

            IsPalindromeTheme {
                Scaffold { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Greeting(
                            name = "",
                            modifier = Modifier.padding(innerPadding)
                        )

                        TextField(
                            value = input,
                            onValueChange = {
                                input = it
                                isPalindrome = null
                            },
                            modifier = Modifier,
                            label = { Text("Input") }
                        )

                        Button(
                            modifier = Modifier,
                            onClick = {

                                isPalindrome = (checkPalindrome(input))
                            },
                            content = { Text("Click") }
                        )

                        isPalindrome?.let{ isPalindrome ->
                            if (isPalindrome) {
                                Text("$input is a palindrome")
                            } else {
                                Text("$input is not a palindrome")
                            }
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello! Is it a Palindrome?",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    IsPalindromeTheme {
        Greeting("Android")
    }
}

fun checkPalindrome(s: String): Boolean {
    val stripInput = s.trim().filter { c -> c.isLetterOrDigit() }.lowercase()
    return if (stripInput.isEmpty()) {
        return true
    } else if (stripInput.length % 2 == 0) {
        stripInput.substring(0, stripInput.length / 2) == stripInput.substring(stripInput.length / 2).reversed()
    } else {
        stripInput.substring(0, stripInput.length / 2) == stripInput.substring((stripInput.length / 2) + 1).reversed()
    }
}

private fun checkPalindrome2(s: String): Boolean {
    val filteredS = s.filter { c -> c.isLetterOrDigit() }.lowercase()
    return filteredS == filteredS.reversed()
}