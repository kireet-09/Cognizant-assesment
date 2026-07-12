package com.cognizant.ems.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

/**
 * Exercise 7: Auditing Configuration
 * Enables automatic tracking of created and modified timestamps for entities
 */
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class AuditingConfig {

    /**
     * Provide auditor information for entity auditing
     * In a real application, this would return the current logged-in user
     */
    @Bean(name = "auditorProvider")
    public AuditorAware<String> auditorAware() {
        return () -> Optional.of("SYSTEM");
    }
}
