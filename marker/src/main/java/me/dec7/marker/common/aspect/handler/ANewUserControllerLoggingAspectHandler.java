package me.dec7.marker.common.aspect.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import me.dec7.marker.common.aspect.core.handler.AbstractAspectHandler;
import me.dec7.marker.common.aspect.core.template.AspectParameterStore;

@Component
public class ANewUserControllerLoggingAspectHandler extends AbstractAspectHandler {
	
	private static final Logger log = LoggerFactory.getLogger(ANewUserControllerLoggingAspectHandler.class);

	@Override
	public void before(AspectParameterStore store) {
		log.debug("A NewUserControlerLoggingHandler");
	}
	
	

}
