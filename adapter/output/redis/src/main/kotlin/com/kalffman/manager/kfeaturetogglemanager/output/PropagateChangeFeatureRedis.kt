package com.kalffman.manager.kfeaturetogglemanager.output

import com.kalffman.manager.kfeaturetogglemanager.entity.redis.RFeature
import com.kalffman.manager.kfeaturetogglemanager.output.dto.FeatureOutputDTO
import com.kalffman.manager.kfeaturetogglemanager.repository.redis.FeatureRepository
import org.springframework.stereotype.Service

@Service
class PropagateChangeFeatureRedis(
    private val featureRepository: FeatureRepository
) : PropagateChange<FeatureOutputDTO> {

    override fun propagate(entity: FeatureOutputDTO) {
        featureRepository.save(RFeature(entity))
    }
}
