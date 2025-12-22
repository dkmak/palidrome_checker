package com.example.ispalindrome

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.ispalindrome.ui.theme.IsPalindromeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IsPalindromeApp()
        }
    }
}

/*
* Stateful Composable - IsPalindromeApp is the owner of the state
* */
@Composable
fun IsPalindromeApp() {
    // input is something you might consider saving along with configuration changes
    var input: String by rememberSaveable { mutableStateOf("") }

    /*
    * remember - store an object in memory across recompositions
    * mutableStateOf - state holder that triggers recompositions when it changes
    * */
    var isPalindrome: Boolean? by remember { mutableStateOf(null) }

    IsPalindromeTheme {
        Scaffold { innerPadding ->
            IsPalindromeScreen(
                modifier = Modifier.padding(innerPadding),
                inputValue = input,
                onInputValueChanged = {
                    input = it
                    isPalindrome = null
                },
                isPalindromeResult = isPalindrome,
                onCheckPalindromeClicked = { isPalindrome = (checkPalindrome(input)) }
            )
        }
    }
}

@Preview
@Composable
fun IsPalindromeAppPreview() {
    IsPalindromeApp()
}

/*
* IsPalindromeScreen is stateless (no remember here).
* You can use this anywhere in your app, not just this Activity
* Makes it easy to test.
* */
@Composable
fun IsPalindromeScreen(
    modifier: Modifier,
    inputValue: String,
    onInputValueChanged: (String) -> Unit, // a lambda that takes a String and returns nothing
    isPalindromeResult: Boolean?,
    onCheckPalindromeClicked: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Hello! Is it a Palindrome?"
        )
        TextField(
            modifier = Modifier,
            value = inputValue,
            onValueChange = onInputValueChanged,
            label = { Text("Input") }
        )
        Button(
            modifier = Modifier,
            onClick = onCheckPalindromeClicked,
            content = { Text("Click & Check") }
        )

        isPalindromeResult?.let { isPalindrome ->
            if (isPalindrome) {
                Text("$inputValue is a palindrome")
            } else {
                Text("$inputValue is not a palindrome")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun IsPalindromeContentPreview() {
    IsPalindromeTheme {
        IsPalindromeScreen(
            modifier = Modifier,
            inputValue = "testInput",
            onInputValueChanged = {},
            onCheckPalindromeClicked = {},
            isPalindromeResult = null,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun IsPalindromeContentTruePreview() {
    IsPalindromeTheme {
        IsPalindromeScreen(
            modifier = Modifier,
            inputValue = "testInput",
            onInputValueChanged = {},
            onCheckPalindromeClicked = {},
            isPalindromeResult = true,
        )
    }
}