package com.kalffman.manager.kfeaturetogglemanager.entity.redis

import com.kalffman.manager.kfeaturetogglemanager.output.dto.FeatureOutputDTO
import org.springframework.data.redis.core.RedisHash
import java.util.UUID

@RedisHash("Feature")
data class RFeature(
    val id: Long = 0L,
    val name: String = "",
    val description: String = "",
    val enabled: Boolean = false,
    val externalId: UUID = UUID.randomUUID(),
) {
    constructor(dto: FeatureOutputDTO) : this(
        dto.id,
        dto.name,
        dto.description,
        dto.enabled,
        dto.externalId
    )
}
