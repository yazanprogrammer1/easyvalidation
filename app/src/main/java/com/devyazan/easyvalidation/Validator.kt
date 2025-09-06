package com.devyazan.easyvalidation

import android.util.Patterns

object Validator {
    fun isEmailValid(email: String) = email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    fun isPasswordValid(password: String, minLength: Int = 6) =
        password.length >= minLength && password.any { it.isDigit() } && password.any { it.isLetter() }
    fun isPhoneValid(phone: String) = phone.isNotEmpty() && Patterns.PHONE.matcher(phone).matches()
    fun isNotEmpty(text: String) = text.isNotEmpty()
    fun customRegex(value: String, pattern: Regex) = pattern.matches(value)
}