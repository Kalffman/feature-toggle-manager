package com.kalffman.manager.kfeaturetogglemanager.output

import com.kalffman.manager.kfeaturetogglemanager.output.dto.OFeatureDTO
import java.util.UUID

interface ReadFeature {
    fun find(): List<OFeatureDTO>

    fun find(id: Long): OFeatureDTO

    fun find(id: UUID): OFeatureDTO

}
