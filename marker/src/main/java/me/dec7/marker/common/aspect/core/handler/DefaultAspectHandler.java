package me.dec7.marker.common.aspect.core.handler;

import me.dec7.marker.common.aspect.core.template.AspectParameterStore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DefaultAspectHandler extends AbstractAspectHandler {

	private static final Logger log = LoggerFactory.getLogger(DefaultAspectHandler.class);

	@Override
	public void before(AspectParameterStore store) {
		log.debug("default aspectj before");
	}
	
	@Override
	public void after(AspectParameterStore store) {
		log.debug("default aspectj after");
	}

	@Override
	public void afterReturning(AspectParameterStore store) {
		log.debug("default aspectj afterReturning");
	}

	@Override
	public void afterThrowing(AspectParameterStore store) {
		log.debug("default aspectj afterThrowing");
	}
	
}
