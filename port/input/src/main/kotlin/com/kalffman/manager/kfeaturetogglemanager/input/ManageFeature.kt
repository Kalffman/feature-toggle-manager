package com.kalffman.manager.kfeaturetogglemanager.input

import com.kalffman.manager.kfeaturetogglemanager.input.dto.FeatureInputDTO

interface ManageFeature {
    fun create(feature: FeatureInputDTO)
    fun retrieve(featureId: Long)
    fun update(feature: FeatureInputDTO)
    fun delete(feature: FeatureInputDTO)
}
