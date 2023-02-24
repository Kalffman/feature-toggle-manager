package com.kalffman.manager.kfeaturetogglemanager.output.dto

import java.time.LocalDateTime
import java.util.UUID

data class OFeatureDTO(
    val id: Long? = null,
    val externalId: UUID? = null,
    val name: String,
    val description: String? = null,
    val enabled: Boolean = false,
    val validAfter: LocalDateTime? = null,
    val validBefore: LocalDateTime? = null,
    val created: LocalDateTime? = null,
    val lastUpdate: LocalDateTime? = null,
    val rules: List<Rule>? = null
) {
    data class Rule(
        val id: Long? = null,
        val field: String,
        val ruleType: String,
        val referenceValue: Map<String, Any>? = null,
        val operationType: String,
        val composition: Rule? = null,
        val compositionType: String? = null
    )
}
