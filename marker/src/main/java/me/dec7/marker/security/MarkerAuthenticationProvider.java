package me.dec7.marker.security;

import java.util.List;

import me.dec7.marker.entity.User;
import me.dec7.marker.service.UserService;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class MarkerAuthenticationProvider implements AuthenticationProvider {
	
	private static final Logger log = LoggerFactory.getLogger(MarkerAuthenticationProvider.class);

	@Autowired
	private UserService userService;
	
//	@Autowired
//	private UserDetailsService userDetailsService;
	
//	@Autowired
//	public void setUserService(UserService userService) {
//		this.userService = userService;
//	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String email = (String) authentication.getPrincipal();
		String password = (String) authentication.getCredentials();
		
//		UserDetails userDetails = userDetailsService.loadUserByUsername(email);
		User user = userService.findByEmail(email);
		String userRole = user.getRoles().toString();
		
		if (!email.equals(user.getEmail()) || !password.equals(user.getPassword()) || StringUtils.isBlank(userRole)) {
			throw new BadCredentialsException("Bad credentials");
		}
		
		log.debug(user.toString());
		
		@SuppressWarnings("unchecked")
		List<GrantedAuthority> roles = (List<GrantedAuthority>) user.getAuthorities();
		
		UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(email, password, roles);
		result.setDetails(new User(email, password));
		
		return result;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
