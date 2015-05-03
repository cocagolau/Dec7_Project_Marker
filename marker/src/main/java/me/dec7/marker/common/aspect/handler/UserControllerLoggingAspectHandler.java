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
import org.springframework.stereotype.Component;

@Component
public class UserControllerLoggingAspectHandler extends AbstractAspectHandler {
	
	private static final String KEY_EMAIL = "email";

	private static final Logger LOGGER = LoggerFactory.getLogger(UserControllerLoggingAspectHandler.class);

	@Autowired
	private LogService logService;
	
	@Autowired
	private UserService userService;
	
	
	@Override
	public void before(AspectParameterStore store) {
		String email =  null;
		try {
			email = (String) store.get(KEY_EMAIL) + "@gmail.com";
			String value = store.getValue();
			LOGGER.debug("before value: " + value + ", email: " + email);
			
			User user = userService.findByEmail(email);
			Log log = new Log(user, value);
			logService.insert(log);
		} catch(Exception e) {
			LOGGER.debug(e.getMessage());
		}
	}
	
	@Override
	public void after(AspectParameterStore store) {
		String email =  null;
		try {
			email = (String) store.get(KEY_EMAIL) + "@gmail.com";
			String value = store.getValue();
			LOGGER.debug("after value: " + value + ", email: " + email);
			
			User user = userService.findByEmail(email);
			Log log = new Log(user, value);
			logService.insert(log);
		} catch (Exception e) {
			LOGGER.debug(e.getMessage());
		}
	}

	@Override
	public void afterReturning(AspectParameterStore store) {
		String email =  null;
		try {
			email = (String) store.get(KEY_EMAIL) + "@gmail.com";
			String value = store.getValue();
			LOGGER.debug("afterReturning value: " + value + ", email: " + email);
			
			User user = userService.findByEmail(email);
			Log log = new Log(user, value);
			logService.insert(log);
		} catch (Exception e) {
			LOGGER.debug(e.getMessage());
		}
	}

	@Override
	public void afterThrowing(AspectParameterStore store) {
		String email =  null;
		try {
			email = (String) store.get(KEY_EMAIL) + "@gmail.com";
			String value = store.getValue();
			LOGGER.debug("afterThrowing value: " + value + ", email: " + email);
			
			User user = userService.findByEmail(email);
			Log log = new Log(user, value);
			logService.insert(log);
		} catch (Exception e) {
			LOGGER.debug(e.getMessage());
		}
	}

}
