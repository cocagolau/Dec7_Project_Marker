package me.dec7.marker.common.logging.core.provider;

public abstract class AbstractLoggingProvider implements LoggingProvider {

	@Override
	public void before() { }
	
	@Override
	public void after() { }

	@Override
	public void afterReturning() { }

	@Override
	public void afterThrowing() { }

}
