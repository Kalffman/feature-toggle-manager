package com.kalffman.manager.kfeaturetogglemanager.output.dto

import java.util.UUID

data class FeatureOutputDTO(
    val id: Long = 0L,
    val name: String = "",
    val description: String = "",
    val enabled: Boolean = false,
    val externalId: UUID = UUID.randomUUID()
)
