package com.kalffman.manager.kfeaturetogglemanager.output.postgres

import com.kalffman.manager.kfeaturetogglemanager.entity.postgres.PostgresFeature
import com.kalffman.manager.kfeaturetogglemanager.output.CreateFeature
import com.kalffman.manager.kfeaturetogglemanager.output.ReadFeature
import com.kalffman.manager.kfeaturetogglemanager.output.dto.OFeatureDTO
import com.kalffman.manager.kfeaturetogglemanager.repository.postgres.PostgresFeatureRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.lang.Exception
import java.util.*

@Service
class PostgresFeatureCRUD(
    private val postgresFeatureRepository: PostgresFeatureRepository
) : CreateFeature, ReadFeature {

    private val logger = LoggerFactory.getLogger(PostgresFeatureCRUD::class.java)

    override fun create(feature: OFeatureDTO): OFeatureDTO {
        logger.info("c=PostgresFeatureCRUD, m=create, status=started, feature=$feature")

        try {
            return postgresFeatureRepository.save(PostgresFeature(feature)).toFeatureOutputDTO().also {
                logger.info("c=PostgresFeatureCRUD, m=create, status=finished, feature=$it")
            }
        } catch (ex: Exception) {
            logger.error("c=PostgresFeatureCRUD, m=create, status=error, feature=$feature", ex)

            throw ex
        }
    }

    override fun find(): List<OFeatureDTO> {
        logger.info("c=PostgresFeatureCRUD, m=find, status=started")

        try {
            return postgresFeatureRepository.findAll().map { it.toFeatureOutputDTO() }.also {
                logger.info("c=PostgresFeatureCRUD, m=find, status=finished")
            }
        } catch (ex: Exception) {
            logger.error("c=PostgresFeatureCRUD, m=find, status=error", ex)

            throw ex
        }
    }

    override fun find(id: Long): OFeatureDTO? {
        logger.info("c=PostgresFeatureCRUD, m=find, status=started, id=$id")

        try {
            return postgresFeatureRepository.findById(id).orElse(null)?.toFeatureOutputDTO().also {
                logger.info("c=PostgresFeatureCRUD, m=find, status=finished, id=$id")
            }
        } catch (ex: Exception) {
            logger.error("c=PostgresFeatureCRUD, m=find, status=started, id=$id", ex)

            throw ex
        }
    }

    override fun find(id: UUID): OFeatureDTO? {
        logger.info("c=PostgresFeatureCRUD, m=find, status=started, id=$id")

        try {
            return postgresFeatureRepository.findByExternalId(id).orElse(null)?.toFeatureOutputDTO().also {
                logger.info("c=PostgresFeatureCRUD, m=find, status=started, id=$id")
            }
        } catch (ex: Exception) {
            logger.error("c=PostgresFeatureCRUD, m=find, status=started, id=$id")
            
            throw ex
        }
    }
}
