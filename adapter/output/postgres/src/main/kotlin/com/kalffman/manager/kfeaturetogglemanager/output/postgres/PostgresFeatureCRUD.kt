package com.kalffman.manager.kfeaturetogglemanager.output.postgres

import com.kalffman.manager.kfeaturetogglemanager.entity.postgres.PostgresFeature
import com.kalffman.manager.kfeaturetogglemanager.output.CreateFeature
import com.kalffman.manager.kfeaturetogglemanager.output.DeleteFeature
import com.kalffman.manager.kfeaturetogglemanager.output.ReadFeature
import com.kalffman.manager.kfeaturetogglemanager.output.UpdateFeature
import com.kalffman.manager.kfeaturetogglemanager.output.dto.OFeatureDTO
import com.kalffman.manager.kfeaturetogglemanager.output.postgres.exception.PostgresLayerException
import com.kalffman.manager.kfeaturetogglemanager.repository.postgres.PostgresFeatureRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.Exception
import java.util.UUID

@Service
class PostgresFeatureCRUD(
    private val postgresFeatureRepository: PostgresFeatureRepository
) : CreateFeature, ReadFeature, UpdateFeature, DeleteFeature {

    private val logger = LoggerFactory.getLogger(PostgresFeatureCRUD::class.java)

    override fun create(feature: OFeatureDTO): OFeatureDTO {
        logger.info("c=PostgresFeatureCRUD, m=create, status=started, feature=$feature")

        try {
            return postgresFeatureRepository.saveAndFlush(PostgresFeature(feature)).toFeatureOutputDTO().also {
                logger.debug("c=PostgresFeatureCRUD, m=create, status=finished, feature=$it")
            }
        } catch (ex: Exception) {
            logger.error("c=PostgresFeatureCRUD, m=create, status=error, feature=$feature", ex)

            throw PostgresLayerException(ex.message, ex)
        }
    }

    override fun find(): List<OFeatureDTO> {
        logger.info("c=PostgresFeatureCRUD, m=find, status=started")

        try {
            return postgresFeatureRepository.findAll().map { it.toFeatureOutputDTO() }.also {
                logger.debug("c=PostgresFeatureCRUD, m=find, status=finished")
            }
        } catch (ex: Exception) {
            logger.error("c=PostgresFeatureCRUD, m=find, status=error", ex)

            throw PostgresLayerException(ex.message, ex)
        }
    }

    override fun find(id: Long): OFeatureDTO? {
        logger.info("c=PostgresFeatureCRUD, m=find, status=started, id=$id")

        try {
            return postgresFeatureRepository.findById(id).orElse(null)?.toFeatureOutputDTO().also {
                logger.debug("c=PostgresFeatureCRUD, m=find, status=finished, id=$id")
            }
        } catch (ex: Exception) {
            logger.error("c=PostgresFeatureCRUD, m=find, status=error, id=$id", ex)

            throw PostgresLayerException(ex.message, ex)
        }
    }

    override fun find(id: UUID): OFeatureDTO? {
        logger.info("c=PostgresFeatureCRUD, m=find, status=started, id=$id")

        try {
            return postgresFeatureRepository.findByExternalId(id).orElse(null)?.toFeatureOutputDTO().also {
                logger.debug("c=PostgresFeatureCRUD, m=find, status=finished, id=$id")
            }
        } catch (ex: Exception) {
            logger.error("c=PostgresFeatureCRUD, m=find, status=error, id=$id", ex)

            throw PostgresLayerException(ex.message, ex)
        }
    }

    @Transactional
    override fun update(feature: OFeatureDTO): OFeatureDTO {
        logger.info("c=PostgresFeatureCRUD, m=update, status=started, feature=$feature")

        try {
            return postgresFeatureRepository.save(PostgresFeature(feature)).toFeatureOutputDTO().also {
                logger.debug("c=PostgresFeatureCRUD, m=update, status=finished, feature=$feature")
            }
        } catch (ex: Exception) {
            logger.error("c=PostgresFeatureCRUD, m=update, status=error, feature=$feature", ex)

            throw PostgresLayerException(ex.message, ex)
        }
    }

    @Transactional
    override fun delete(feature: OFeatureDTO) {
        logger.info("c=PostgresFeatureCRUD, m=delete, status=started, feature=$feature")

        try {
            postgresFeatureRepository.delete(PostgresFeature(feature)).also {
                logger.debug("c=PostgresFeatureCRUD, m=delete, status=finished, feature=$feature")
            }
        } catch (ex: Exception) {
            logger.error("c=PostgresFeatureCRUD, m=delete, status=error, feature=$feature", ex)

            throw PostgresLayerException(ex.message, ex)
        }
    }
}
