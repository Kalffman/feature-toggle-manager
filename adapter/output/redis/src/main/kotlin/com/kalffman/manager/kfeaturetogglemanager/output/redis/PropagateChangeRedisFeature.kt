package com.kalffman.manager.kfeaturetogglemanager.output.redis

import com.kalffman.manager.kfeaturetogglemanager.entity.redis.RedisFeature
import com.kalffman.manager.kfeaturetogglemanager.output.PropagateChange
import com.kalffman.manager.kfeaturetogglemanager.output.dto.OFeatureDTO
import com.kalffman.manager.kfeaturetogglemanager.repository.redis.RedisFeatureRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.lang.Exception

@Service
class PropagateChangeRedisFeature(
    private val redisFeatureRepository: RedisFeatureRepository
) : PropagateChange<OFeatureDTO> {

    private val logger = LoggerFactory.getLogger(PropagateChangeRedisFeature::class.java)

    override fun propagate(entity: OFeatureDTO) {
        logger.info("c=PropagateFeatureChange, m=propagate, status=started, entity=$entity")

        try {
            redisFeatureRepository.save(RedisFeature(entity)).also {
                logger.info("c=PropagateFeatureChange, m=propagate, status=finished, entity=$it")
            }
        } catch (ex: Exception) {
            logger.error("c=PropagateFeatureChange, m=propagate, status=error, entity=$entity", ex)

            throw ex
        }
    }
}
