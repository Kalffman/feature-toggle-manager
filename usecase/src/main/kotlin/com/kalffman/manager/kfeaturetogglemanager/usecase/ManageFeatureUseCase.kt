package com.kalffman.manager.kfeaturetogglemanager.usecase

import com.kalffman.manager.kfeaturetogglemanager.input.ManageFeature
import com.kalffman.manager.kfeaturetogglemanager.input.dto.IFeatureDTO
import com.kalffman.manager.kfeaturetogglemanager.input.dto.NewIFeatureDTO
import com.kalffman.manager.kfeaturetogglemanager.output.CreateFeature
import com.kalffman.manager.kfeaturetogglemanager.output.PropagateChange
import com.kalffman.manager.kfeaturetogglemanager.output.ReadFeature
import com.kalffman.manager.kfeaturetogglemanager.output.dto.OFeatureDTO
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ManageFeatureUseCase(
    private val createFeature: CreateFeature,
    private val readFeature: ReadFeature,
    private val propagateChange: PropagateChange<OFeatureDTO>
) : ManageFeature {

    val logger: Logger = LoggerFactory.getLogger(ManageFeatureUseCase::class.java)

    override fun create(feature: NewIFeatureDTO): IFeatureDTO {
        logger.info("c=ManageFeatureUseCase, m=create, status=started, feature=$feature")

        val featureCreated = createFeature.create(feature.toOutputDTO()).also {
            propagateChange.propagate(it)
        }

        return featureCreated.toInputDTO().also {
            logger.info("c=ManageFeatureUseCase, m=create, status=finished,feature=$it")
        }
    }

    override fun retrieve(): List<IFeatureDTO> {
        logger.info("c=ManageFeatureUseCase, m=retrieve, status=started")

        return readFeature.find().map { it.toInputDTO() }.also {
            logger.info("c=ManageFeatureUseCase, m=retrieve, status=finished")
        }
    }

    override fun retrieve(featureId: Long): IFeatureDTO? {
        logger.info("c=ManageFeatureUseCase, m=retrieve, featureId=$featureId, status=started")

        return readFeature.find(featureId)?.toInputDTO().also {
            logger.info("c=ManageFeatureUseCase, m=retrieve, featureId=$featureId, status=finished")
        }
    }

    override fun retrieve(featureId: UUID): IFeatureDTO? {
        logger.info("c=ManageFeatureUseCase, m=retrieve, featureId=$featureId, status=started")

        return readFeature.find(featureId)?.toInputDTO().also {
            logger.info("c=ManageFeatureUseCase, m=retrieve, featureId=$featureId, status=finished")
        }
    }

    override fun update(feature: IFeatureDTO): IFeatureDTO {
        TODO("Not yet implemented")
    }

    override fun delete(feature: IFeatureDTO) {
        TODO("Not yet implemented")
    }
}
