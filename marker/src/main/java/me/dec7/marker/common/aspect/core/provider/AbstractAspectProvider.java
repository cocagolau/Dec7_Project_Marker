package me.dec7.marker.common.aspect.core.provider;

import me.dec7.marker.common.aspect.core.template.AspectParameterStore;

public abstract class AbstractAspectProvider implements AspectProvider {

	@Override
	public void before(AspectParameterStore store) { }
	
	@Override
	public void after(AspectParameterStore store) { }

	@Override
	public void afterReturning(AspectParameterStore store) { }

	@Override
	public void afterThrowing(AspectParameterStore store) { }

}
