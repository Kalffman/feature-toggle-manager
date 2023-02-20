package com.kalffman.manager.kfeaturetogglemanager.entity.postgres

import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "feature")
data class PFeature(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = -1,
    val name: String = "",
    val description: String? = null,
    val externalId: UUID
)
