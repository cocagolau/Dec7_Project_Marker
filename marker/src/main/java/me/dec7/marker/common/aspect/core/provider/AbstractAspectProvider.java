package me.dec7.marker.common.aspect.core.provider;

import java.util.Map;

public abstract class AbstractAspectProvider implements AspectProvider {

	@Override
	public void before(Map<String, Object> attributes) { }
	
	@Override
	public void after(Map<String, Object> attributes) { }

	@Override
	public void afterReturning(Map<String, Object> attributes) { }

	@Override
	public void afterThrowing(Map<String, Object> attributes) { }

}
