package com.kalffman.manager.kfeaturetogglemanager.entity.redis

import org.springframework.data.redis.core.RedisHash
import java.util.UUID

@RedisHash("Feature")
data class Feature(
    val id: Long = 0L,
    val name: String = "",
    val description: String = "",
    val enabled: Boolean = false,
    val externalId: UUID = UUID.randomUUID(),
)
