package me.dec7.marker.common.aspect.core.handler;

import me.dec7.marker.common.aspect.core.template.AspectParameterStore;

public interface AspectHandler {
	
	void before(final AspectParameterStore store);

	void after(final AspectParameterStore store);
	
	void afterReturning(final AspectParameterStore store);

	void afterThrowing(final AspectParameterStore store);

}
