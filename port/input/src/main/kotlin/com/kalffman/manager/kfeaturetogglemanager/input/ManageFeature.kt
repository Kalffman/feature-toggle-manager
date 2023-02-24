package com.kalffman.manager.kfeaturetogglemanager.input

import com.kalffman.manager.kfeaturetogglemanager.input.dto.IFeatureDTO
import com.kalffman.manager.kfeaturetogglemanager.input.dto.NewIFeatureDTO
import java.util.UUID

interface ManageFeature {
    fun create(feature: NewIFeatureDTO): IFeatureDTO
    fun retrieve(): List<IFeatureDTO>

    fun retrieve(featureId: Long): IFeatureDTO

    fun retrieve(featureId: UUID): IFeatureDTO
    fun update(feature: IFeatureDTO)
    fun delete(feature: IFeatureDTO)
}
