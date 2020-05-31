package kr.ac.univ.lab.common.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class AuditorAwareImpl implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		// Spring Security를 통한 Auditor 매핑
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
        if (authentication == null || !authentication.isAuthenticated()) {
        	// 추후 변경 필요
            return Optional.of("root");
        }

        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        
        return Optional.of(userDetails.getUsername());
	}
}