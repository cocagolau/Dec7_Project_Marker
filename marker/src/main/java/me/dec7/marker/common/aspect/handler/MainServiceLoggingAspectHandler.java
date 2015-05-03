package me.dec7.marker.common.aspect.handler;

import me.dec7.marker.common.aspect.core.handler.AbstractAspectHandler;
import me.dec7.marker.common.aspect.core.template.AspectParameterStore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MainServiceLoggingAspectHandler extends AbstractAspectHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(MainServiceLoggingAspectHandler.class);
	
	@Override
	public void before(AspectParameterStore store) {
		LOGGER.debug("MainService aspectj before");
	}
	
	@Override
	public void after(AspectParameterStore store) {
		LOGGER.debug("MainService aspectj after");
	}

	@Override
	public void afterReturning(AspectParameterStore store) {
		LOGGER.debug("MainService aspectj afterReturning");
	}

	@Override
	public void afterThrowing(AspectParameterStore store) {
		LOGGER.debug("MainService aspectj afterThrowing");
	}

}
