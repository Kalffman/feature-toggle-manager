package com.kalffman.manager.kfeaturetogglemanager.entity.redis

import com.kalffman.manager.kfeaturetogglemanager.output.postgres.dto.FeatureOutputDTO
import org.springframework.data.redis.core.RedisHash
import java.util.UUID

@RedisHash("Feature")
data class RFeature(
    val id: UUID = UUID.randomUUID(),
    val name: String = "",
    val description: String = "",
    val enabled: Boolean = false
) {
    constructor(dto: FeatureOutputDTO) : this(
        dto.externalId,
        dto.name,
        dto.description,
        dto.enabled
    )
}
