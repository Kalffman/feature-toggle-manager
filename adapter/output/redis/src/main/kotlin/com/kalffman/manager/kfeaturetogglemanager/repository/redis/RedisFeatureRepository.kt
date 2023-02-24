package com.kalffman.manager.kfeaturetogglemanager.repository.redis

import com.kalffman.manager.kfeaturetogglemanager.entity.redis.RedisFeature
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RedisFeatureRepository : CrudRepository<RedisFeature, String>
