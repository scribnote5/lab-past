package kr.ac.univ.lab.common.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class AccessCheck {
	public static Boolean isAccess(String createdBy) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		boolean result = false;
		
		if("anonymousUser".equals(authentication.getPrincipal()) || authentication == null || !authentication.isAuthenticated()) {
			return false; 
		} else {
			UserDetails userDetails	 = (UserDetails) authentication.getPrincipal();
			String username = userDetails.getUsername();

			/*
			 * root: 모든 접근 허용
			 * manager: 사용자가 같은 경우 접근 허용
			 * general: 사용자가 같은 경우 접근 허용
			 * anonymous: 모든 접근 불가
			 */

			for (GrantedAuthority grantedAuthority : userDetails.getAuthorities()) {
				switch (grantedAuthority.getAuthority()) {
				case "root":
					result = true;
					break;
				case "manager":
					if(!username.equals(createdBy) && !"root".equals(grantedAuthority.getAuthority())) result = false; 
					else result = true;
					break;
				case "general":
					if(username.equals(createdBy)) result = true;
					break;
				default:
					break;
				}
			}
		}
		
		return result;
	}
	
	public static Boolean isAnonymousUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		boolean result = false;
		
		if ("anonymousUser".equals(authentication.getPrincipal()) || authentication == null || !authentication.isAuthenticated()) {
			result = true;
		}
		
		return result;
	}
}