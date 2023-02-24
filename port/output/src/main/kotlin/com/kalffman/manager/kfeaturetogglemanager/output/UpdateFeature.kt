package com.kalffman.manager.kfeaturetogglemanager.output

import com.kalffman.manager.kfeaturetogglemanager.output.dto.OFeatureDTO

interface UpdateFeature {
    fun update(feature: OFeatureDTO)
}
