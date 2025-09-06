package com.devyazan.easyvalidation

import com.devyazan.easyvalidation.Validator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ValidationBuilder(private val value: String) {

    private var errorMessage: String? = null

    fun email(msg: String = "Invalid Email") = apply {
        if (!Validator.isEmailValid(value)) errorMessage = msg
    }

    fun password(minLength: Int = 6, msg: String = "Invalid Password") = apply {
        if (!Validator.isPasswordValid(value, minLength)) errorMessage = msg
    }

    fun phone(msg: String = "Invalid Phone") = apply {
        if (!Validator.isPhoneValid(value)) errorMessage = msg
    }

    fun notEmpty(msg: String = "Field cannot be empty") = apply {
        if (!Validator.isNotEmpty(value)) errorMessage = msg
    }

    fun custom(pattern: Regex, msg: String) = apply {
        if (!Validator.customRegex(value, pattern)) errorMessage = msg
    }

    fun onResult(callback: (ValidationResult) -> Unit) {
        callback(ValidationResult(errorMessage == null, errorMessage))
    }

    fun asFlow(): Flow<ValidationResult> = flow {
        emit(ValidationResult(errorMessage == null, errorMessage))
    }
}