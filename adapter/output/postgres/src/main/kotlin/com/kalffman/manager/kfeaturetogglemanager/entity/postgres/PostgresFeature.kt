package com.kalffman.manager.kfeaturetogglemanager.entity.postgres

import com.kalffman.manager.kfeaturetogglemanager.output.dto.OFeatureDTO
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.PrePersist
import jakarta.persistence.PreUpdate
import jakarta.persistence.Table
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(schema = "feature_manager", name = "feature")
data class PostgresFeature(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column(name = "external_id", nullable = false)
    var externalId: UUID? = null,
    @Column(name = "name", nullable = false)
    var name: String,
    @Column(name = "description")
    var description: String? = null,
    @Column(name = "enabled", nullable = false)
    var enabled: Boolean = false,
    @Column(name = "valid_after")
    var validAfter: LocalDateTime? = null,
    @Column(name = "valid_before")
    var validBefore: LocalDateTime? = null,
    @Column(name = "created", nullable = false)
    var created: LocalDateTime? = null,
    @Column(name = "last_update", nullable = false)
    var lastUpdate: LocalDateTime? = null,
    @ManyToMany
    @JoinTable(
        name = "feature_rule",
        schema = "feature_manager",
        joinColumns = [JoinColumn(name = "feature_id")],
        inverseJoinColumns = [JoinColumn(name = "rule_id")]
    )
    var rules: List<PostgresRule>? = null
) {

    constructor(dto: OFeatureDTO) : this(
        dto.id,
        dto.externalId,
        dto.name,
        dto.description,
        dto.enabled,
        dto.validAfter,
        dto.validBefore,
        rules = dto.rules?.map { PostgresRule(it) }
    )
    @PrePersist
    fun prePersist() {
        this.externalId = UUID.randomUUID()
        this.created = LocalDateTime.now()
        this.lastUpdate = LocalDateTime.now()
        validateFeature()
    }

    @PreUpdate
    fun preUpdate() {
        this.lastUpdate = LocalDateTime.now()
        validateFeature()
    }

    private fun validateFeature() {
        if (this.validAfter != null && this.validBefore != null) {
            if (this.validAfter!!.isBefore(this.validBefore)) {
                throw IllegalArgumentException("this.validAfter cannot be before this.validBefore")
            }
        }
    }

    fun toFeatureOutputDTO(): OFeatureDTO = OFeatureDTO(
        id,
        externalId,
        name,
        description,
        enabled,
        validAfter,
        validBefore,
        created,
        lastUpdate,
        rules?.map { it.toFeatureOutputDTO() }
    )
}
