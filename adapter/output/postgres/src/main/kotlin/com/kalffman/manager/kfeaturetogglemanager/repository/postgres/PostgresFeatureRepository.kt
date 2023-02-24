package com.kalffman.manager.kfeaturetogglemanager.repository.postgres

import com.kalffman.manager.kfeaturetogglemanager.entity.postgres.PostgresFeature
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PostgresFeatureRepository : JpaRepository<PostgresFeature, Long>
