package com.kalffman.manager.kfeaturetogglemanager.usecase

import com.kalffman.manager.kfeaturetogglemanager.input.ManageFeature
import com.kalffman.manager.kfeaturetogglemanager.input.dto.FeatureInputDTO
import com.kalffman.manager.kfeaturetogglemanager.output.postgres.PropagateChange
import com.kalffman.manager.kfeaturetogglemanager.output.postgres.dto.FeatureOutputDTO
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class FeatureUseCase(
    private val propagateChange: PropagateChange<FeatureOutputDTO>
) : ManageFeature {

    val logger: Logger = LoggerFactory.getLogger(FeatureUseCase::class.java)

    override fun create(feature: FeatureInputDTO) {
        logger.info("m=create, feature=$feature")

        propagateChange.propagate(feature.toOutputDTO())
    }

    override fun retrieve(featureId: Long) {
        TODO("Not yet implemented")
    }

    override fun update(feature: FeatureInputDTO) {
        TODO("Not yet implemented")
    }

    override fun delete(feature: FeatureInputDTO) {
        TODO("Not yet implemented")
    }
}
