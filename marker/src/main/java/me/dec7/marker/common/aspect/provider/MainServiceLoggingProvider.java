package me.dec7.marker.common.aspect.provider;

import me.dec7.marker.common.aspect.core.provider.AbstractAspectProvider;
import me.dec7.marker.common.aspect.core.template.AspectParameterStore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MainServiceLoggingProvider extends AbstractAspectProvider {

	private static final Logger LOGGER = LoggerFactory.getLogger(MainServiceLoggingProvider.class);
	
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
