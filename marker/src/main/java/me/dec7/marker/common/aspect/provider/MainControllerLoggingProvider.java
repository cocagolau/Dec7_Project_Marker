package me.dec7.marker.common.aspect.provider;

import me.dec7.marker.common.aspect.core.provider.AbstractAspectProvider;
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
public class MainControllerLoggingProvider extends AbstractAspectProvider {

	private static final Logger LOGGER = LoggerFactory.getLogger(MainControllerLoggingProvider.class);
	
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
