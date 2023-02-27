package com.kalffman.manager.kfeaturetogglemanager.annotation

import com.kalffman.manager.kfeaturetogglemanager.validator.NewIFeatureDTOValidator
import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Target(
    AnnotationTarget.VALUE_PARAMETER,
    AnnotationTarget.ANNOTATION_CLASS,
    AnnotationTarget.FUNCTION
)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [NewIFeatureDTOValidator::class])
annotation class ValidateNewIFeatureDTO(
    val message: String = "Invalid NewIFeatureDTO object",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
