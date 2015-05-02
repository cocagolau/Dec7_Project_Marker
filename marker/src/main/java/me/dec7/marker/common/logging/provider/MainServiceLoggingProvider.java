package me.dec7.marker.common.logging.provider;

import me.dec7.marker.common.logging.core.provider.AbstractLoggingProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("mainServiceLoggingProvider")
public class MainServiceLoggingProvider extends AbstractLoggingProvider {

	private static final Logger LOGGER = LoggerFactory.getLogger(MainServiceLoggingProvider.class);
	
	@Override
	public void before() {
		LOGGER.debug("MainService aspectj before");
	}
	
	@Override
	public void after() {
		LOGGER.debug("MainService aspectj after");
	}

	@Override
	public void afterReturning() {
		LOGGER.debug("MainService aspectj afterReturning");
	}

	@Override
	public void afterThrowing() {
		LOGGER.debug("MainService aspectj afterThrowing");
	}

}
