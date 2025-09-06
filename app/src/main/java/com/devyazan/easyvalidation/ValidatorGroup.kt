package com.devyazan.easyvalidation

class ValidatorGroup {
    private val validations = mutableListOf<ValidationBuilder>()

    fun add(validation: ValidationBuilder): ValidatorGroup {
        validations.add(validation)
        return this
    }

    fun validate(): List<ValidationResult> {
        return validations.map {
            var result: ValidationResult? = null
            it.onResult { res -> result = res }
            result!!
        }
    }
}
