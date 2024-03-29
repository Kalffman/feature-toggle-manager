package com.kalffman.manager.kfeaturetogglemanager

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan

@SpringBootApplication
@ConfigurationPropertiesScan
class KFeatureToggleManagerApplication

fun main(args: Array<String>) {
    SpringApplication.run(KFeatureToggleManagerApplication::class.java, *args)
}
