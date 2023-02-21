package com.kalffman.manager.kfeaturetogglemanager.output.postgres

import com.kalffman.manager.kfeaturetogglemanager.output.postgres.dto.FeatureOutputDTO
import com.kalffman.manager.kfeaturetogglemanager.repository.postgres.FeatureRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class FeatureCRUD(
    private val featureRepository: FeatureRepository
): CreateFeature, FindFeature, UpdateFeature, DeleteFeature {

    override fun create(feature: FeatureOutputDTO) {
        TODO("Not yet implemented")
    }

    override fun delete(feature: FeatureOutputDTO) {
        TODO("Not yet implemented")
    }

    override fun find(): List<FeatureOutputDTO> {
        TODO("Not yet implemented")
    }

    override fun find(id: UUID): FeatureOutputDTO {
        TODO("Not yet implemented")
    }

    override fun update(feature: FeatureOutputDTO) {
        TODO("Not yet implemented")
    }
}