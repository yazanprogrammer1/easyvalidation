package com.devyazan.sampleapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.devyazan.easyvalidation.ValidatedTextField
import com.devyazan.easyvalidation.ValidationBuilder
import com.devyazan.easyvalidation.ValidatorGroup
import com.devyazan.sampleapp.ui.theme.EasyValidationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EasyValidationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                }
            }
        }
    }
}

@Composable
fun Validation() {
    val emailInput = "example@test.com"
    ValidationBuilder(emailInput)
        .notEmpty()
        .email()
        .onResult { result ->
            if (result.isValid) println("Email valid ✅")
            else println("Error: ${result.errorMessage}")
        }

    // Compose usage
    ValidatedTextField(value = emailInput, onValueChange = { /* update state */ }) {
        notEmpty()
        email()
    }
}

@Composable
fun SignUpScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }

    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }
    var phoneError by remember { mutableStateOf<String?>(null) }
    var nameError by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Create Account", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        // Full Name
        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
                ValidationBuilder(it).notEmpty("Name is required")
                    .onResult { res -> nameError = res.errorMessage }
            },
            label = { Text("Full Name") },
            isError = nameError != null,
            modifier = Modifier.fillMaxWidth()
        )
        if (nameError != null) Text(nameError!!, color = Color.Red)

        Spacer(modifier = Modifier.height(8.dp))

        // Email
        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                ValidationBuilder(it).notEmpty().email()
                    .onResult { res -> emailError = res.errorMessage }
            },
            label = { Text("Email") },
            isError = emailError != null,
            modifier = Modifier.fillMaxWidth()
        )
        if (emailError != null) Text(emailError!!, color = Color.Red)

        Spacer(modifier = Modifier.height(8.dp))

        // Phone
        OutlinedTextField(
            value = phone,
            onValueChange = {
                phone = it
                ValidationBuilder(it).notEmpty().phone()
                    .onResult { res -> phoneError = res.errorMessage }
            },
            label = { Text("Phone Number") },
            isError = phoneError != null,
            modifier = Modifier.fillMaxWidth()
        )
        if (phoneError != null) Text(phoneError!!, color = Color.Red)

        Spacer(modifier = Modifier.height(8.dp))

        // Password
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                ValidationBuilder(it).notEmpty().password(minLength = 8)
                    .onResult { res -> passwordError = res.errorMessage }
            },
            label = { Text("Password") },
            isError = passwordError != null,
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )
        if (passwordError != null) Text(passwordError!!, color = Color.Red)

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                // التحقق النهائي عند الضغط
                val groupResults = ValidatorGroup()
                    .add(ValidationBuilder(name).notEmpty())
                    .add(ValidationBuilder(email).notEmpty().email())
                    .add(ValidationBuilder(phone).notEmpty().phone())
                    .add(ValidationBuilder(password).notEmpty().password(minLength = 8))
                    .validate()

                if (groupResults.all { it.isValid }) {
                    // نجاح التسجيل
                    println("Sign up success 🎉")
                } else {
                    println("Fix the errors ❌")
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Sign Up")
        }
    }
}