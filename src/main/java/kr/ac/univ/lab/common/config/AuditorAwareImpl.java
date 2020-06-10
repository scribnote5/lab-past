package kr.ac.univ.lab.common.config;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import kr.ac.univ.lab.LabApplication;

public class AuditorAwareImpl implements AuditorAware<String> {
	
	private static final Logger logger = LoggerFactory.getLogger(LabApplication.class);
	
	@Override
	public Optional<String> getCurrentAuditor() {
		// Spring Security를 통한 Auditor 매핑
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
        if (authentication == null || !authentication.isAuthenticated()) {
        	// 수정: 추후 변경 필요 
            return Optional.of("root");
        }
        
        logger.info("AuditorAwareImpl: getCurrentAuditor");
        logger.info("authentication: " + authentication);
        logger.info("authentication.getPrincipal(): " + authentication.getPrincipal());
        
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        
        return Optional.of(userDetails.getUsername());
	}
}