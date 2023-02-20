package com.kalffman.manager.kfeaturetogglemanager.input.dto

import java.util.UUID

data class FeatureInputDTO(
    val id: UUID = UUID.randomUUID(),
    val name: String = "",
    val description: String = "",
    val enabled: Boolean = false,
    val externalId: UUID = UUID.randomUUID()
)
