package com.example.toastapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.toastapplication.ui.theme.ToastApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            var input: String by remember{mutableStateOf("")}
            var isPalindrome : Boolean by remember {mutableStateOf(false)}
            ToastApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column {
                        Greeting(
                            name = "Android",
                            modifier = Modifier.padding(innerPadding)
                        )

                        TextField(
                            value = input,
                            onValueChange = { input  = it },
                            modifier = Modifier,
                            label = { Text("Label") }
                        )
                        Button(
                            modifier = Modifier,
                            onClick = {
                                isPalindrome =  (checkPalindrome(input))
                            },
                            content = {Text("Click")}
                        )

                        if (isPalindrome){
                            Text("$input is a palindrome")
                        } else {
                            Text( "$input is not a palindrome")
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
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ToastApplicationTheme {
        Greeting("Android")
    }
}

/*
* even
* odd
* empty string
* */
private fun checkPalindrome(input: String): Boolean {
    return if (input.length % 2 == 0){
        input.substring(0, input.length/2) == input.substring(input.length/2).reversed()
    } else {
        input.substring(0, input.length/2) == input.substring((input.length/2)+1).reversed()
    }
}