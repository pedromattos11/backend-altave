package br.com.altave.backend_altave.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "br.com.altave.backend_altave.repository")
@EnableTransactionManagement
public class JpaConfig {
    
    // Configurações específicas de JPA podem ser adicionadas aqui
    // As configurações principais estão no application.properties
}