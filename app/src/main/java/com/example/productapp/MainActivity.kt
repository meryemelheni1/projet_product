package com.example.productapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.productapp.ui.theme.ProductAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProductAppTheme {
                LoginScreen()
            }
        }
    }
}


@Composable
fun LoginScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val PREFS_NAME = "myPrefs"
    val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    var savedPath by remember { mutableStateOf(prefs.getString("path", "") ?: "") }
    var path by remember { mutableStateOf("com.example.productapp.Homepage") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Login", fontSize = 32.sp)
        Image(painter = painterResource(R.drawable.login2), contentDescription = null, modifier = Modifier.fillMaxWidth(), contentScale = ContentScale.FillWidth)
        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
            Spacer(modifier = Modifier.height(8.dp))
        }

        Button(onClick = {
            if (email.isEmpty() || password.isEmpty()) {
                prefs.edit().putString("path", path).apply()
                errorMessage = "Please fill in both fields"
            } else {
                val intent = Intent(context, HomepageActivity::class.java)
                context.startActivity(intent)
            }
        }) {
            Text("Login")
        }
        Button(onClick = {
            val intent = Intent(context, SecondActivity::class.java)
            context.startActivity(intent)
        }) {
            Text("Sign Up")
        }

        Button(onClick = {
            val intent = Intent(context, NewFormActivity::class.java)
            context.startActivity(intent)
        }) {
            Text("Forgot password?")
        }
    }
}


@Composable
@Preview(showBackground = true)
fun LoginPreview() {
    ProductAppTheme {
        LoginScreen()
    }
}
