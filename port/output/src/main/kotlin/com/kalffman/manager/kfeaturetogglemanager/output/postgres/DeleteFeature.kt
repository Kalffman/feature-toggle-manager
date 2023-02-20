package com.kalffman.manager.kfeaturetogglemanager.output.postgres

import com.kalffman.manager.kfeaturetogglemanager.output.postgres.dto.FeatureOutputDTO

interface DeleteFeature {
    fun delete(feature: FeatureOutputDTO)
}