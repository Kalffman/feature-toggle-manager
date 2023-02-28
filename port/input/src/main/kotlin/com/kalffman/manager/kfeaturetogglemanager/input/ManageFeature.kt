package com.kalffman.manager.kfeaturetogglemanager.input

import com.kalffman.manager.kfeaturetogglemanager.input.dto.IFeatureDTO
import com.kalffman.manager.kfeaturetogglemanager.input.dto.NewIFeatureDTO
import com.kalffman.manager.kfeaturetogglemanager.input.dto.UpdateIFeatureDTO
import java.util.UUID

interface ManageFeature {
    fun create(feature: NewIFeatureDTO): IFeatureDTO
    fun retrieve(): List<IFeatureDTO>
    fun retrieve(featureId: Long): IFeatureDTO?
    fun retrieve(featureId: UUID): IFeatureDTO?
    fun update(id: Long, changes: UpdateIFeatureDTO): IFeatureDTO
    fun update(id: UUID, changes: UpdateIFeatureDTO): IFeatureDTO
    fun delete(feature: IFeatureDTO)
    fun delete(id: Long)
    fun delete(id: UUID)
}
