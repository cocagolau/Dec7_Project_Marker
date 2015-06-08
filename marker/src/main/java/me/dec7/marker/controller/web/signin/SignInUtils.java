package me.dec7.marker.controller.web.signin;

import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public class SignInUtils {
	
	/**
	 * Programmatically signs in the user with the given the user ID.
	 */
	public static void signin(String userId) {
		SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userId, null, null));	
	}
	
//	public static Authentication signInUser(User user) {
//		List<GrantedAuthority> authorities = UserDetailsServiceImpl.createAuthorities(user);
//		SpringSecurityUser springSecurityUser = new SpringSecurityUser(user, authorities);
//		Authentication authentication = new UsernamePasswordAuthenticationToken(springSecurityUser, user.getPassword(), authorities);
//		SecurityContextHolder.getContext().setAuthentication(authentication);
//		
//		return authentication;
//	}
}


