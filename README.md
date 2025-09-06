# 🚀 EasyValidation

**EasyValidation** is a lightweight yet powerful Android library designed to simplify data validation in your apps.  
It works seamlessly with both **Jetpack Compose** and **traditional Views**, making it super easy to integrate into any project.  

---

## ✨ Features
- ✅ Validate Email, Password, Phone, Empty Fields  
- ✅ Support for **Custom Regex** rules  
- ✅ Works with **Jetpack Compose** & **XML Views**  
- ✅ Easy integration with **MVVM (LiveData / StateFlow)**  
- ✅ **ValidatorGroup** to validate multiple fields at once  
- ✅ Localization support (multi-language error messages)  
- ✅ Lightweight & developer-friendly  

---

## 📦 Installation

Add this in your `settings.gradle`:
```gradle
dependencyResolutionManagement {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```
Then in your `build.gradle (app)`:
```gradle
dependencies {
    implementation 'com.github.yazanprogrammer1:easyvalidation:1.0.0'
}
```
## 🛠️ Usage

🔹 1. Basic Validation
```
ValidationBuilder("example@test.com")
    .notEmpty()
    .email()
    .onResult { result ->
        if (result.isValid) {
            println("Valid ✅")
        } else {
            println(result.errorMessage)
        }
    }
```

🔹 2. Custom Regex
```
ValidationBuilder("AB-12345")
    .regex("^[A-Z]{2}-\\d{5}$", "Invalid custom code format")
    .onResult { result ->
        if (result.isValid) println("Valid ✅")
        else println(result.errorMessage)
    }
```

🔹 3. Validate Multiple Fields (ValidatorGroup)
```
val emailValidator = ValidationBuilder("example@test.com").email()
val passwordValidator = ValidationBuilder("12345678").minLength(8)

ValidatorGroup(emailValidator, passwordValidator)
    .validateAll { results ->
        if (results.all { it.isValid }) {
            println("All fields are valid ✅")
        } else {
            results.filter { !it.isValid }.forEach {
                println("Error: ${it.errorMessage}")
            }
        }
    }
```
🔹 4. Jetpack Compose Example (Register Screen)
```
@Composable
fun RegisterScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            isError = error.isNotEmpty()
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            isError = error.isNotEmpty()
        )

        Button(
            onClick = {
                ValidatorGroup(
                    ValidationBuilder(email).notEmpty().email(),
                    ValidationBuilder(password).notEmpty().minLength(8)
                ).validateAll { results ->
                    if (results.all { it.isValid }) {
                        error = ""
                        println("Register success ✅")
                    } else {
                        error = results.first { !it.isValid }.errorMessage ?: "Invalid input"
                    }
                }
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Register")
        }

        if (error.isNotEmpty()) {
            Text(error, color = Color.Red, modifier = Modifier.padding(top = 8.dp))
        }
    }
}
```

## 🌍 Localization
You can customize error messages for multiple languages:
```
ValidationBuilder("")
    .notEmpty("This field cannot be empty")
    .onResult { result ->
        println(result.errorMessage) // "This field cannot be empty"
    }
```

## 📖 Roadmap
⏳ Kotlin Multiplatform support
⏳ Compose Validation Decorators
⏳ New rules (ID cards, credit cards, etc.)

## 🤝 Contribution
1-Fork the repo
2-Create a new branch ```feature/my-feature```
3-Commit and push your changes
4-Open a Pull Request

## 📜 License
MIT License © 2025 Yazan Abu Ali
