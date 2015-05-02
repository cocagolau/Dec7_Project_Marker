package me.dec7.marker.common.logging.core.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("defaultLoggingProvider")
public class DefaultLoggingProvider extends AbstractLoggingProvider {

	private static final Logger log = LoggerFactory.getLogger(DefaultLoggingProvider.class);

	@Override
	public void before() {
		log.debug("default aspectj before");
	}
	
	@Override
	public void after() {
		log.debug("default aspectj after");
	}

	@Override
	public void afterReturning() {
		log.debug("default aspectj afterReturning");
	}

	@Override
	public void afterThrowing() {
		log.debug("default aspectj afterThrowing");
	}
	
}
