package me.dec7.marker.common.aspect.provider;

import java.util.Map;

import me.dec7.marker.common.aspect.core.provider.AbstractAspectProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MainServiceLoggingProvider extends AbstractAspectProvider {

	private static final Logger LOGGER = LoggerFactory.getLogger(MainServiceLoggingProvider.class);
	
	@Override
	public void before(Map<String, Object> attributes) {
		LOGGER.debug("MainService aspectj before");
	}
	
	@Override
	public void after(Map<String, Object> attributes) {
		LOGGER.debug("MainService aspectj after");
	}

	@Override
	public void afterReturning(Map<String, Object> attributes) {
		LOGGER.debug("MainService aspectj afterReturning");
	}

	@Override
	public void afterThrowing(Map<String, Object> attributes) {
		LOGGER.debug("MainService aspectj afterThrowing");
	}

}
