package me.dec7.marker.common.logging.core.provider;

public interface LoggingProvider {

	void before();

	void after();
	
	void afterReturning();

	void afterThrowing();

}
