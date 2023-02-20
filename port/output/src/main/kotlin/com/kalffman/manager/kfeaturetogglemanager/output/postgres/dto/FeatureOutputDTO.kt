package com.kalffman.manager.kfeaturetogglemanager.output.postgres.dto

import java.util.UUID

data class FeatureOutputDTO(
    val id: UUID? = null,
    val name: String,
    val description: String? = null,
    val enabled: Boolean = false
)
