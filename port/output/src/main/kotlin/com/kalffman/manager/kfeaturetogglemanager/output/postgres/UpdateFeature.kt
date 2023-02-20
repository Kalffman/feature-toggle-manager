package com.kalffman.manager.kfeaturetogglemanager.output.postgres

import com.kalffman.manager.kfeaturetogglemanager.output.postgres.dto.FeatureOutputDTO

interface UpdateFeature {
    fun update(feature: FeatureOutputDTO)
}