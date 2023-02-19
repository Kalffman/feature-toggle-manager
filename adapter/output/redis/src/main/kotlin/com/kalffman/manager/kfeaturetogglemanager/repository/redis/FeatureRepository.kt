package com.kalffman.manager.kfeaturetogglemanager.repository.redis

import com.kalffman.manager.kfeaturetogglemanager.entity.redis.Feature
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface FeatureRepository: CrudRepository<Feature, String>