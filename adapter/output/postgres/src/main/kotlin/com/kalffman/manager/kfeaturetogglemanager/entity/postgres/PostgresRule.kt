package com.kalffman.manager.kfeaturetogglemanager.entity.postgres

import com.kalffman.manager.kfeaturetogglemanager.output.dto.OFeatureDTO
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.PrePersist
import jakarta.persistence.PreUpdate
import jakarta.persistence.Table
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes

@Entity
@Table(schema = "feature_manager", name = "rule")
data class PostgresRule(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column(name = "field", nullable = false)
    var field: String,
    @Enumerated(EnumType.STRING)
    @Column(name = "rule_type", nullable = false)
    var ruleType: RuleType,
    @Column(name = "reference_value", columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    var referenceValue: Map<String, Any>? = null,
    @Enumerated(EnumType.STRING)
    @Column(name = "operation_type", nullable = false)
    var operationType: OperationType,
    @Enumerated(EnumType.STRING)
    @Column(name = "rule_composition_type")
    var ruleCompositionType: RuleCompositionType? = null,
    @ManyToOne(
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    @JoinColumn(name = "rule_composition_id", referencedColumnName = "id")
    var ruleComposition: PostgresRule? = null
) {

    constructor(dto: OFeatureDTO.Rule) : this(
        dto.id,
        dto.field,
        RuleType.valueOf(dto.ruleType),
        dto.referenceValue,
        OperationType.valueOf(dto.operationType),
        dto.compositionType?.let { RuleCompositionType.valueOf(it) },
        dto.composition?.let { PostgresRule(it) }
    )

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

    enum class RuleCompositionType {
        AND,
        OR
    }

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
        if ((this.ruleType == RuleType.REFERENCE) and (this.referenceValue == null))
            throw IllegalArgumentException("referenceValue cannot be null if ruleType is RuleType.REFERENCE")

        if ((this.ruleComposition != null) xor (this.ruleCompositionType != null))
            throw IllegalArgumentException("if ruleComposition was setted ruleCompositionType need to be to")
    }

    fun toFeatureOutputDTO(): OFeatureDTO.Rule = OFeatureDTO.Rule(
        id = this.id,
        field = this.field,
        ruleType = this.ruleType.name,
        referenceValue = this.referenceValue,
        operationType = this.operationType.name,
        composition = this.ruleComposition?.toFeatureOutputDTO(),
        compositionType = this.ruleCompositionType?.name
    )
}
