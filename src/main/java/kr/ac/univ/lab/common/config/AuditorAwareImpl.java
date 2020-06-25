package kr.ac.univ.lab.common.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import kr.ac.univ.lab.common.error.exception.AnonymousUserException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuditorAwareImpl implements AuditorAware<String> {
	@Override
	public Optional<String> getCurrentAuditor() {
		// Spring Security를 통한 Auditor 매핑
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
        if (authentication == null || !authentication.isAuthenticated()) {
        	// 수정: 추후 변경 필요 
            return Optional.of("root");
        }
        
        log.info("AuditorAwareImpl: getCurrentAuditor");
        log.info("authentication: " + authentication);
        log.info("authentication.getPrincipal(): " + authentication.getPrincipal());
        
        if("anonymousUser".equals(authentication.getPrincipal())) {
        	throw new AnonymousUserException();
        }
        
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        
        return Optional.of(userDetails.getUsername());
	}
}