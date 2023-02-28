package com.kalffman.manager.kfeaturetogglemanager.input.dto

import java.time.LocalDateTime

data class UpdateIFeatureDTO(
    val name: String,
    val description: String? = null,
    val enabled: Boolean = true,
    val validAfter: LocalDateTime? = null,
    val validBefore: LocalDateTime? = null,
    val rules: List<Long>? = null
)
