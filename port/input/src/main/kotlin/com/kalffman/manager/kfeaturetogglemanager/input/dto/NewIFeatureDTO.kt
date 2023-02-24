package com.kalffman.manager.kfeaturetogglemanager.input.dto

import java.time.LocalDateTime

data class NewIFeatureDTO(
    val name: String,
    val description: String? = null,
    val enabled: Boolean = true,
    val validAfter: LocalDateTime? = null,
    val validBefore: LocalDateTime? = null,
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
