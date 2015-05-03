package me.dec7.marker.common.aspect.handler;

import me.dec7.marker.common.aspect.core.handler.AbstractAspectHandler;
import me.dec7.marker.common.aspect.core.template.AspectParameterStore;
import me.dec7.marker.entity.Log;
import me.dec7.marker.entity.User;
import me.dec7.marker.service.LogService;
import me.dec7.marker.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainControllerLoggingAspectHandler extends AbstractAspectHandler {

	@Autowired
	private LogService logService;
	
	@Autowired
	private UserService userService;
	
	
	@Override
	public void before(AspectParameterStore store) {
		User user = userService.findByEmail("aaaaaaaa@gmail.com");
		Log log = new Log(user, "MainController aspectj before");
		logService.insert(log);
	}
	
	@Override
	public void after(AspectParameterStore store) {
		User user = userService.findByEmail("aaaaaaaa@gmail.com");
		Log log = new Log(user, "MainController aspectj after");
		logService.insert(log);
	}

	@Override
	public void afterReturning(AspectParameterStore store) {
		User user = userService.findByEmail("aaaaaaaa@gmail.com");
		Log log = new Log(user, "MainController aspectj afterReturning");
		logService.insert(log);
	}

	@Override
	public void afterThrowing(AspectParameterStore store) {
		User user = userService.findByEmail("aaaaaaaa@gmail.com");
		Log log = new Log(user, "MainController aspectj afterThrowing");
		logService.insert(log);
	}

}
