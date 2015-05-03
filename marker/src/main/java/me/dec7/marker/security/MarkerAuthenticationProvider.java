package me.dec7.marker.security;

import me.dec7.marker.common.aspect.annotation.AspectMethod;
import me.dec7.marker.common.aspect.annotation.AspectMethod.State;
import me.dec7.marker.common.aspect.annotation.AspectParam;
import me.dec7.marker.common.aspect.handler.MainControllerLoggingHandler;
import me.dec7.marker.common.aspect.handler.MarkerAuthenticationProviderLoggingHandler;
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
	
	@Override
	@AspectMethod(
			state=State.ALL ,
			handler=MarkerAuthenticationProviderLoggingHandler.class,
			value="Authenticate")
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		@AspectParam
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
