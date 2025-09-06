package com.devyazan.easyvalidation

data class ValidationResult(
    val isValid: Boolean,
    val errorMessage: String? = null
)
