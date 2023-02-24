package com.kalffman.manager.kfeaturetogglemanager.output

import com.kalffman.manager.kfeaturetogglemanager.output.dto.OFeatureDTO

interface CreateFeature {
    fun create(feature: OFeatureDTO): OFeatureDTO
}
