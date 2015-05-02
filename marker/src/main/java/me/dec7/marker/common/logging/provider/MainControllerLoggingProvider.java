package me.dec7.marker.common.logging.provider;

import me.dec7.marker.common.logging.core.provider.AbstractLoggingProvider;
import me.dec7.marker.entity.Log;
import me.dec7.marker.entity.User;
import me.dec7.marker.service.LogService;
import me.dec7.marker.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("mainControllerLoggingProvider")
public class MainControllerLoggingProvider extends AbstractLoggingProvider {

	private static final Logger LOGGER = LoggerFactory.getLogger(MainControllerLoggingProvider.class);
	
	@Autowired
	private LogService logService;
	
	@Autowired
	private UserService userService;
	
	
	@Override
//	@Async("taskExecutor")
	public void before() {
		User user = userService.findByEmail("aaaaaaaa@gmail.com");
		Log log = new Log(user, "MainController aspectj before");
		logService.insert(log);
	}
	
	@Override
//	@Async("taskExecutor")
	public void after() {
		User user = userService.findByEmail("aaaaaaaa@gmail.com");
		Log log = new Log(user, "MainController aspectj after");
		logService.insert(log);
	}

	@Override
	public void afterReturning() {
		User user = userService.findByEmail("aaaaaaaa@gmail.com");
		Log log = new Log(user, "MainController aspectj afterReturning");
		logService.insert(log);
	}

	@Override
//	@Async("taskExecutor")
	public void afterThrowing() {
		User user = userService.findByEmail("aaaaaaaa@gmail.com");
		Log log = new Log(user, "MainController aspectj afterThrowing");
		logService.insert(log);
	}

}
