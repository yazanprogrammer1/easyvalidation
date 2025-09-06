package com.devyazan.easyvalidation

import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color

@Composable
fun ValidatedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    validator: ValidationBuilder.() -> Unit
) {
    var error by remember { mutableStateOf<String?>(null) }
    BasicTextField(
        value = value,
        onValueChange = {
            onValueChange(it)
            ValidationBuilder(it).apply(validator).onResult { result ->
                error = result.errorMessage
            }
        }
    )
    if (error != null) {
        Text(text = error!!, color = Color.Red)
    }
}
