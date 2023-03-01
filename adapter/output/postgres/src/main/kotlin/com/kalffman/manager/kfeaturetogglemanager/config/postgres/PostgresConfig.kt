package com.kalffman.manager.kfeaturetogglemanager.config.postgres

import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.jdbc.datasource.DriverManagerDataSource
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager
import java.util.Properties
import javax.sql.DataSource

@Configuration
@EnableJpaRepositories(
    basePackages = [
        "com.kalffman.manager.kfeaturetogglemanager.entity.postgres",
        "com.kalffman.manager.kfeaturetogglemanager.repository.postgres"
    ],
    entityManagerFactoryRef = "postgresEntityManager",
    transactionManagerRef = "postgresTransactionManager"
)
class PostgresConfig(val conn: PostgresConnectionProperties) {

    private val logger = LoggerFactory.getLogger(PostgresConfig::class.java)

    @Bean
    @Primary
    fun postgresDataSource(): DataSource {
        logger.debug("c=PostgresConfig, m=dataSource, conn=$conn")

        return DriverManagerDataSource().apply {
            this.setDriverClassName("org.postgresql.Driver")
            this.username = conn.user
            this.password = conn.password
            this.url = "jdbc:postgresql://${conn.host}:${conn.port}/${conn.db}"
        }
    }

    @Bean
    @Primary
    fun extraPostgresProperties(): Properties {
        return Properties().apply {
            this.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect")
        }
    }

    @Bean
    @Primary
    fun postgresEntityManager(
        postgresDataSource: DataSource,
        extraPostgresProperties: Properties
    ): LocalContainerEntityManagerFactoryBean {

        return LocalContainerEntityManagerFactoryBean().apply {
            this.dataSource = postgresDataSource
            this.jpaVendorAdapter = HibernateJpaVendorAdapter()
            this.setPackagesToScan("com.kalffman.manager.kfeaturetogglemanager.entity.postgres")
            this.setJpaProperties(extraPostgresProperties)
        }
    }

    @Bean
    @Primary
    fun postgresTransactionManager(
        postgresEntityManager: LocalContainerEntityManagerFactoryBean
    ): PlatformTransactionManager {
        return JpaTransactionManager().apply {
            entityManagerFactory = postgresEntityManager.`object`
        }
    }
}
