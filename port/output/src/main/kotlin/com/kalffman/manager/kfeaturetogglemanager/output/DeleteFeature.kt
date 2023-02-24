package com.kalffman.manager.kfeaturetogglemanager.output

import com.kalffman.manager.kfeaturetogglemanager.output.dto.OFeatureDTO

interface DeleteFeature {
    fun delete(feature: OFeatureDTO)
}
