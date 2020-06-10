package kr.ac.univ.lab.common.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class CommentAccess {
	
	public static Boolean isAccess(String createdBy) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = (UserDetails) principal;
		String username = userDetails.getUsername();
		boolean result = false;

		/*
		 * root: 접근 허용
		 * manager: 접근 허용
		 * general: 사용자가 같은 경우 접근 허용
		 * anonymous: 접근 불가
		 */
		for (GrantedAuthority grantedAuthority : userDetails.getAuthorities()) {
			switch (grantedAuthority.getAuthority()) {
			case "root":
				result = true;
				break;
			case "manager":
				result = true;
				break;
			case "general":
				if (username.equals(createdBy))
					result = true;
				break;
			default:
				result = false;
				break;
			}
		}

		return result;
	}
	
	public static Boolean isAnonymousUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		boolean result = false;
		
		if (authentication.getPrincipal().equals("anonymousUser") || authentication == null || !authentication.isAuthenticated()) {
			result = true;
		}
		
		
		return result;
	}
}