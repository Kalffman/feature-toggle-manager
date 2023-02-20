package com.kalffman.manager.kfeaturetogglemanager.output.redis

import com.kalffman.manager.kfeaturetogglemanager.entity.redis.RFeature
import com.kalffman.manager.kfeaturetogglemanager.output.postgres.PropagateChange
import com.kalffman.manager.kfeaturetogglemanager.output.postgres.dto.FeatureOutputDTO
import com.kalffman.manager.kfeaturetogglemanager.repository.redis.FeatureRepository
import org.springframework.stereotype.Service

@Service
class PropagateFeatureChange(
    private val featureRepository: FeatureRepository
) : PropagateChange<FeatureOutputDTO> {

    override fun propagate(entity: FeatureOutputDTO) {
        featureRepository.save(RFeature(entity))
    }
}
