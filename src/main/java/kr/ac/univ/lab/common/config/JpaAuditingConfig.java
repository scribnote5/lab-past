package kr.ac.univ.lab.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing(auditorAwareRef = "AuditorAwareImpl")
@Configuration
public class JpaAuditingConfig {
	@Bean 
	public AuditorAware<String> AuditorAwareImpl() { 
		return new AuditorAwareImpl(); 
	}
}
