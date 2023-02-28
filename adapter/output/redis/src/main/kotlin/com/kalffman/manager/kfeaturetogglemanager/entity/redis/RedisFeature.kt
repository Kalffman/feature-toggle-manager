package com.kalffman.manager.kfeaturetogglemanager.entity.redis

import com.kalffman.manager.kfeaturetogglemanager.output.dto.OFeatureDTO
import org.springframework.data.redis.core.RedisHash
import java.util.UUID

@RedisHash("Feature")
data class RedisFeature(
    val id: UUID,
    val name: String,
    val description: String? = null,
    val enabled: Boolean,
    val rules: List<RRule>? = null
) {

    constructor(dto: OFeatureDTO) : this(
        dto.externalId!!,
        dto.name,
        dto.description,
        dto.enabled,
        dto.rules?.map { RRule(it) }
    )

    data class RRule(
        val field: String,
        val ruleType: String,
        val referenceValue: Map<String, Any>? = null,
        val operationType: String,
        val composition: RRule? = null,
        val compositionType: String? = null
    ) {
        constructor(dto: OFeatureDTO.Rule) : this(
            dto.field,
            dto.ruleType,
            dto.referenceValue,
            dto.operationType,
            dto.composition?.let { RRule(it) },
            dto.compositionType
        )
    }
}
