package com.kalffman.manager.kfeaturetogglemanager.usecase

import com.kalffman.manager.kfeaturetogglemanager.input.ManageFeature
import com.kalffman.manager.kfeaturetogglemanager.input.dto.IFeatureDTO
import com.kalffman.manager.kfeaturetogglemanager.input.dto.NewIFeatureDTO
import com.kalffman.manager.kfeaturetogglemanager.input.dto.UpdateIFeatureDTO
import com.kalffman.manager.kfeaturetogglemanager.input.exception.InputException
import com.kalffman.manager.kfeaturetogglemanager.output.CreateFeature
import com.kalffman.manager.kfeaturetogglemanager.output.DeleteFeature
import com.kalffman.manager.kfeaturetogglemanager.output.PropagateChange
import com.kalffman.manager.kfeaturetogglemanager.output.ReadFeature
import com.kalffman.manager.kfeaturetogglemanager.output.UpdateFeature
import com.kalffman.manager.kfeaturetogglemanager.output.dto.OFeatureDTO
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ManageFeatureUseCase(
    private val createFeature: CreateFeature,
    private val readFeature: ReadFeature,
    private val updateFeature: UpdateFeature,
    private val deleteFeature: DeleteFeature,
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
        logger.info("c=ManageFeatureUseCase, m=retrieve, status=started, featureId=$featureId")

        return readFeature.find(featureId)?.toInputDTO()?.also {
            logger.info("c=ManageFeatureUseCase, m=retrieve, status=finished, featureId=${it.id}")
        }
    }

    override fun retrieve(featureId: UUID): IFeatureDTO? {
        logger.info("c=ManageFeatureUseCase, m=retrieve, status=started, featureId=$featureId")

        return readFeature.find(featureId)?.toInputDTO()?.also {
            logger.info("c=ManageFeatureUseCase, m=retrieve, status=finished, featureId=${it.externalId}")
        }
    }

    override fun update(id: Long, changes: UpdateIFeatureDTO): IFeatureDTO {
        logger.info("c=ManageFeatureUseCase, m=update, status=started, id=$id,  changes=$changes")

        val featFound = retrieve(id) ?: throw InputException("usecase", "Feature not exist")

        val featToUpdate = featFound.copy(
            name = changes.name,
            description = changes.description,
            enabled = changes.enabled,
            validAfter = changes.validAfter,
            validBefore = changes.validBefore
        )

        return updateFeature.update(featToUpdate.toOutputDTO()).toInputDTO().also {
            logger.info("c=ManageFeatureUseCase, m=update, status=started, feature=$it")
        }
    }

    override fun update(id: UUID, changes: UpdateIFeatureDTO): IFeatureDTO {
        logger.info("c=ManageFeatureUseCase, m=update, status=started, id=$id,  changes=$changes")

        val featFound = retrieve(id) ?: throw InputException("usecase", "Feature not exist")

        val featToUpdate = featFound.copy(
            name = changes.name,
            description = changes.description,
            enabled = changes.enabled,
            validAfter = changes.validAfter,
            validBefore = changes.validBefore
        )

        return updateFeature.update(featToUpdate.toOutputDTO()).toInputDTO().also {
            logger.info("c=ManageFeatureUseCase, m=update, status=started, feature=$it")
        }
    }

    override fun delete(feature: IFeatureDTO) {
        logger.info("c=ManageFeatureUseCase, m=delete, status=started, feature=$feature")

        deleteFeature.delete(feature.toOutputDTO()).also {
            logger.info("c=ManageFeatureUseCase, m=delete, status=finished")
        }
    }

    override fun delete(id: Long) {
        val featToDelete = retrieve(id) ?: throw InputException("usecase", "Feature not exist")

        delete(featToDelete)
    }

    override fun delete(id: UUID) {
        val featToDelete = retrieve(id) ?: throw InputException("usecase", "Feature not exist")

        delete(featToDelete)
    }
}
