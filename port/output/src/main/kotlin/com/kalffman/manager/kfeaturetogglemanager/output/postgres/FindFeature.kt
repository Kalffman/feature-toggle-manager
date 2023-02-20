package com.kalffman.manager.kfeaturetogglemanager.output.postgres

import com.kalffman.manager.kfeaturetogglemanager.output.postgres.dto.FeatureOutputDTO
import java.util.UUID

interface FindFeature {
    fun find(): List<FeatureOutputDTO>

    fun find(id: UUID): FeatureOutputDTO
}