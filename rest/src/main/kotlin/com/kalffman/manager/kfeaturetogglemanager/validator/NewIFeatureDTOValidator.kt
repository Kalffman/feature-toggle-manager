package com.kalffman.manager.kfeaturetogglemanager.validator

import com.kalffman.manager.kfeaturetogglemanager.annotation.ValidateNewIFeatureDTO
import com.kalffman.manager.kfeaturetogglemanager.input.dto.NewIFeatureDTO
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class NewIFeatureDTOValidator : ConstraintValidator<ValidateNewIFeatureDTO, Any> {
    private lateinit var message: String

    override fun initialize(constraintAnnotation: ValidateNewIFeatureDTO) {
        message = constraintAnnotation.message
    }

    override fun isValid(value: Any?, context: ConstraintValidatorContext?): Boolean {
        if (value == null || value !is NewIFeatureDTO) {
            return true
        }

        val nameValue = value.name

        if (!nameValue.matches(Regex("^[A-Z]+(_[A-Z]+)*\$"))) {
            return false
        }

        val validAfterValue = value.validAfter
        val validBeforeValue = value.validBefore

        if (validAfterValue != null && validBeforeValue != null) {
            if (!validAfterValue.isBefore(validBeforeValue)) {
                return false
            }
        }

        value.rules?.let { valueRules ->
            if (valueRules.any { it.ruleType == "REFERENCE" }) {
                val invalidRule = valueRules.find { it.ruleType == "REFERENCE" && it.referenceValue == null }

                if (invalidRule != null) {
                    return false
                }
            }

            if (valueRules.any { it.composition != null }) {
                val invalidRule = valueRules.find { it.composition != null && it.compositionType == null }

                if (invalidRule != null) {
                    return false
                }
            }
        }

        return true
    }
}
