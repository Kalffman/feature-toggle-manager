package com.kalffman.manager.kfeaturetogglemanager.validator

import com.kalffman.manager.kfeaturetogglemanager.annotation.ValidateIFeatureDTO
import com.kalffman.manager.kfeaturetogglemanager.input.dto.IFeatureDTO
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class IFeatureDTOValidator : ConstraintValidator<ValidateIFeatureDTO, Any> {
    private lateinit var message: String

    override fun initialize(constraintAnnotation: ValidateIFeatureDTO) {
        message = constraintAnnotation.message
    }

    override fun isValid(value: Any?, context: ConstraintValidatorContext?): Boolean {
        if (value == null || value !is IFeatureDTO) {
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
