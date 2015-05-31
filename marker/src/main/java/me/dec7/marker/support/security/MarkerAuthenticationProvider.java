package me.dec7.marker.support.security;

import me.dec7.marker.entity.User;
import me.dec7.marker.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class MarkerAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	private UserService userService;
	
//	@AspectMethod(
//		state=State.ALL ,
//		handler=MarkerAuthenticationProviderLoggingHandler.class,
//		value="MarkerAuthenticationProvider")
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String email = (String) authentication.getPrincipal();
		String password = (String) authentication.getCredentials();
		
		User user = userService.findByEmail(email);
		if (!email.equals(user.getEmail())) {
			throw new BadCredentialsException("[Bad credentials] wrong email");
		} else if (!password.equals(user.getPassword())) {
			throw new BadCredentialsException("[Bad credentials] wrong password");
		}
		
		UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(email, password, user.getAuthorities());
		result.setDetails(user);
		
		return result;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
