package com.androidapptemplate

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

@Composable
fun IsPalindromeApp() {
    var input: String by remember { mutableStateOf("") }
    var isPalindrome: Boolean? by remember { mutableStateOf(null) }
    val isInputValid by remember {
        derivedStateOf { !input.contains(" ") && isPalindrome == null }
    }
    IsPalindromeTheme {
        Scaffold { innerPadding ->
            IsPalindromeScreen(
                modifier = Modifier.padding(innerPadding),
                input = input,
                onInputValueChanged = { newInput ->
                    input = newInput
                    isPalindrome = null
                },
                onButtonClicked = {
                    if (input.isNotEmpty()){
                        isPalindrome = checkIfPalindrome(input)
                    }
                },
                isPalindromeResult = isPalindrome,
                isInputValid = isInputValid
            )
        }
    }
}

@Composable
fun IsPalindromeScreen(
    input: String,
    onInputValueChanged: (String) -> Unit,
    onButtonClicked: () -> Unit,
    modifier: Modifier = Modifier,
    isPalindromeResult: Boolean?,
    isInputValid: Boolean
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Is It a Palindrome?",
            modifier = Modifier
        )
        TextField(
            value = input,
            onValueChange = onInputValueChanged,
            modifier = Modifier,
            label = { Text(text = "Enter input") }
        )
        Button(
            onClick = onButtonClicked,
            modifier = modifier,
        ) {
            Text(text = "Check and Find Out!")
        }
        if (!isInputValid) {
            Text(text = "Please remove spaces from your input")
        }
        isPalindromeResult?.let{ result ->
            if (result) {
                Text("$input is a palindrome.")
            } else {
                Text("$input is not a palindrome.")
            }
        }
    }
}

private fun checkIfPalindrome(s: String): Boolean {
    val filteredS = s.filter { c -> c.isLetterOrDigit() }.lowercase()
    return filteredS == filteredS.reversed()
}

@Preview(showBackground = true)
@Composable
fun IsPalindromeScreenPreview() {
    IsPalindromeTheme {
        IsPalindromeScreen(
            input = "input",
            onInputValueChanged = { },
            onButtonClicked = { },
            isPalindromeResult = null,
            isInputValid = true
        )
    }
}