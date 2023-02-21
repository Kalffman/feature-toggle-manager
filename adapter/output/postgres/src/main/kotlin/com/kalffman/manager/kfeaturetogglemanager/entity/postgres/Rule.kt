package com.kalffman.manager.kfeaturetogglemanager.entity.postgres

import jakarta.persistence.GenerationType
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.Column
import jakarta.persistence.Enumerated
import jakarta.persistence.EnumType
import jakarta.persistence.ManyToOne
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.PrePersist
import jakarta.persistence.PreUpdate

@Entity
@Table(schema = "feature_manager", name = "rule")
data class Rule(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column(name = "field", nullable = false)
    var field: String,
    @Enumerated(EnumType.STRING)
    @Column(name = "rule_type", nullable = false)
    var ruleType: RuleType,
    @Column(name = "reference_value", columnDefinition = "jsonb")
    var referenceValue: String? = null,
    @Column(name = "operation_type", nullable = false)
    var operationType: OperationType,
    @Column(name = "rule_composition_type")
    var ruleCompositionType: String? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rule_composition_id", referencedColumnName = "id")
    var ruleComposition: Rule? = null
) {
    init {
        validate()
    }

    @PrePersist
    fun prePersist() {
        validate()
    }
    @PreUpdate
    fun preUpdate() {
        validate()
    }

    private fun validate() {
        if((this.ruleType == RuleType.REFERENCE) and (this.referenceValue == null))
            throw IllegalArgumentException("referenceValue cannot be null if ruleType is RuleType.REFERENCE")

        if((this.ruleComposition != null) xor (this.ruleCompositionType != null))
            throw IllegalArgumentException("if ruleComposition was setted ruleCompositionType need to be to")
    }

    enum class RuleType {
        REFERENCE,
        DYNAMIC
    }

    enum class OperationType {
        EQUAL,
        NOT_EQUAL,
        HIGHER,
        HIGHER_OR_EQUAL,
        LOWER,
        LOWER_OR_EQUAL,
        CONTAINS,
        DISTINCT
    }
}
