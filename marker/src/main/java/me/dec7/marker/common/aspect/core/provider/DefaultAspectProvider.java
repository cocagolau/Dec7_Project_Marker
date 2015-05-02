package me.dec7.marker.common.aspect.core.provider;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DefaultAspectProvider extends AbstractAspectProvider {

	private static final Logger log = LoggerFactory.getLogger(DefaultAspectProvider.class);

	@Override
	public void before(Map<String, Object> attributes) {
		log.debug("default aspectj before");
	}
	
	@Override
	public void after(Map<String, Object> attributes) {
		log.debug("default aspectj after");
	}

	@Override
	public void afterReturning(Map<String, Object> attributes) {
		log.debug("default aspectj afterReturning");
	}

	@Override
	public void afterThrowing(Map<String, Object> attributes) {
		log.debug("default aspectj afterThrowing");
	}
	
}
