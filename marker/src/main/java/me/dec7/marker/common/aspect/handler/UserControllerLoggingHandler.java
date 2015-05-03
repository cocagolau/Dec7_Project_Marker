package me.dec7.marker.common.aspect.handler;

import me.dec7.marker.common.aspect.core.handler.AbstractAspectHandler;
import me.dec7.marker.common.aspect.core.template.AspectParameterStore;
import me.dec7.marker.entity.Log;
import me.dec7.marker.entity.User;
import me.dec7.marker.service.LogService;
import me.dec7.marker.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class UserControllerLoggingHandler extends AbstractAspectHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserControllerLoggingHandler.class);

	@Autowired
	private LogService logService;
	
	@Autowired
	private UserService userService;
	
	
	@Override
	public void before(AspectParameterStore store) {
		String email =  null;
		try {
			email = (String) store.get("emaill");
			LOGGER.debug("proxy in userController logging handler before: email: " + email);
		} catch (IllegalAccessException e) {
			LOGGER.debug(e.getMessage());
		}
		
//		Authentication auth = (Authentication) store.get("auth");
//		String email = (String) auth.getCredentials();
//		
//		User user = userService.findByEmail(email);
//		Log log = new Log(user, "MainController aspectj before");
//		logService.insert(log);
//		LOGGER.debug("proxy in userController logging handler before: email: " + email);
	}
	
	@Override
	public void after(AspectParameterStore store) {
		String email =  null;
		try {
			email = (String) store.get("email");
			LOGGER.debug("proxy in userController logging handler after: email: " + email);
		} catch (IllegalAccessException e) {
			LOGGER.debug(e.getMessage());
		}
		
//		Authentication auth = (Authentication) store.get("auth");
//		String email = (String) auth.getCredentials();
//		
//		User user = userService.findByEmail(email);
//		Log log = new Log(user, "MainController aspectj after");
//		logService.insert(log);
//		LOGGER.debug("proxy in userController logging handler after: email: " + email);
	}

	@Override
	public void afterReturning(AspectParameterStore store) {
		String email =  null;
		try {
			email = (String) store.get("email");
			LOGGER.debug("proxy in userController logging handler afterReturning: email: " + email);
		} catch (IllegalAccessException e) {
			LOGGER.debug(e.getMessage());
		}
//		Authentication auth = (Authentication) store.get("auth");
//		String email = (String) auth.getCredentials();
//		
//		User user = userService.findByEmail(email);
//		Log log = new Log(user, "MainController aspectj afterReturning");
//		logService.insert(log);
//		LOGGER.debug("proxy in userController logging handler afterReturning: email: " + email);
	}

	@Override
	public void afterThrowing(AspectParameterStore store) {
		String email =  null;
		try {
			email = (String) store.get("email");
			LOGGER.debug("proxy in userController logging handler afterThrowing: email: " + email);
		} catch (IllegalAccessException e) {
			LOGGER.debug(e.getMessage());
		}
//		Authentication auth = (Authentication) store.get("auth");
//		String email = (String) auth.getCredentials();
//		
//		User user = userService.findByEmail(email);
//		Log log = new Log(user, "MainController aspectj afterThrowing");
//		logService.insert(log);
//		LOGGER.debug("proxy in userController logging handler afterThrowing: email: " + email);
	}

}
