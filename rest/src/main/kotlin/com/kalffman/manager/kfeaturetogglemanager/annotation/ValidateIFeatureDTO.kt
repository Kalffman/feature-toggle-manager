package com.kalffman.manager.kfeaturetogglemanager.annotation

import com.kalffman.manager.kfeaturetogglemanager.validator.IFeatureDTOValidator
import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Target(
    AnnotationTarget.VALUE_PARAMETER,
    AnnotationTarget.ANNOTATION_CLASS,
    AnnotationTarget.FUNCTION
)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [IFeatureDTOValidator::class])
annotation class ValidateIFeatureDTO(
    val message: String = "Invalid IFeatureDTO object",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
