package br.com.altave.backend_altave.config;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationInitializer;
import org.springframework.boot.autoconfigure.flyway.FlywayProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
@ConditionalOnProperty(name = "spring.flyway.enabled", havingValue = "true", matchIfMissing = true)
public class FlywayConfig {

    private static final Logger logger = LoggerFactory.getLogger(FlywayConfig.class);

    @Bean
    @Primary
    public FlywayMigrationInitializer flywayInitializer(Flyway flyway) {
        return new FlywayMigrationInitializer(flyway, null) {
            @Override
            public void afterPropertiesSet() throws Exception {
                try {
                    // First, try to repair any failed migrations
                    logger.info("Attempting to repair Flyway schema history...");
                    flyway.repair();
                    logger.info("Flyway repair completed successfully");
                } catch (Exception e) {
                    logger.warn("Flyway repair failed, but continuing with migration: {}", e.getMessage());
                }
                
                // Then proceed with normal migration
                super.afterPropertiesSet();
            }
        };
    }
}
