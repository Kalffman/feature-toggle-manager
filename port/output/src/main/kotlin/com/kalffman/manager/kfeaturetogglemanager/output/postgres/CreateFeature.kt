package com.kalffman.manager.kfeaturetogglemanager.output.postgres

import com.kalffman.manager.kfeaturetogglemanager.output.postgres.dto.FeatureOutputDTO

interface CreateFeature {
    fun create(feature: FeatureOutputDTO)
}