package com.kalffman.manager.kfeaturetogglemanager.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
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

    @Bean
    fun jedisConnectionFactory(): JedisConnectionFactory{
        return JedisConnectionFactory(RedisStandaloneConfiguration(redisHost, redisPort))
    }

    @Bean
    fun redisTemplate(connFactory: JedisConnectionFactory): RedisTemplate<String, Any> {
        return RedisTemplate<String, Any>().apply {
            this.connectionFactory = connFactory
        }
    }
}