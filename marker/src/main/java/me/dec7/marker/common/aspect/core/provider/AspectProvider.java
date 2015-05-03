package me.dec7.marker.common.aspect.core.provider;

import me.dec7.marker.common.aspect.core.template.AspectParameterStore;

public interface AspectProvider {

//	void before(final AspectParameterStore store);
//
//	void after(final AspectParameterStore store);
//	
//	void afterReturning(final AspectParameterStore store);
//
//	void afterThrowing(final AspectParameterStore store);
	
	void before(final AspectParameterStore store);

	void after(final AspectParameterStore store);
	
	void afterReturning(final AspectParameterStore store);

	void afterThrowing(final AspectParameterStore store);

}
