package me.dec7.marker.common.aspect.core.provider;

import java.util.Map;

public interface AspectProvider {

	void before(final Map<String, Object> attributes);

	void after(final Map<String, Object> attributes);
	
	void afterReturning(final Map<String, Object> attributes);

	void afterThrowing(final Map<String, Object> attributes);

}
