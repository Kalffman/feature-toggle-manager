package com.kalffman.manager.kfeaturetogglemanager.config.postgres

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "postgres.connection")
data class PostgresConnectionProperties(
    val host: String,
    val port: String,
    val db: String,
    val user: String,
    val password: String
)
