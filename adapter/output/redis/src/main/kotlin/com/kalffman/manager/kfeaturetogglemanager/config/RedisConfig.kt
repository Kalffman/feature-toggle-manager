package com.kalffman.manager.kfeaturetogglemanager.config

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisPassword
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate

@Configuration
class RedisConfig(
    @Value("\${redis.host}")
    private val redisHost: String,
    @Value("\${redis.port}")
    private val redisPort: Int
) {

    val logger = LoggerFactory.getLogger(RedisConfig::class.java)

    @Bean
    fun jedisConnectionFactory(): JedisConnectionFactory {
        logger.info("m=jedisConnectionFactory, redisHost=$redisHost, redisPort=$redisPort")

        val config = RedisStandaloneConfiguration(redisHost, redisPort).apply {
            this.database = 0
            this.password = RedisPassword.none()
        }
        return JedisConnectionFactory(config)
    }

    @Bean
    fun redisTemplate(connFactory: JedisConnectionFactory): RedisTemplate<String, Any> {
        return RedisTemplate<String, Any>().apply {
            this.connectionFactory = connFactory
        }
    }
}
