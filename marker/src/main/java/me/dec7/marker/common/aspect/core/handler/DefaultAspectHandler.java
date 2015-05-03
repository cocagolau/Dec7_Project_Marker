package me.dec7.marker.common.aspect.core.handler;

import me.dec7.marker.common.aspect.core.template.AspectParameterStore;

import org.springframework.stereotype.Component;

@Component
public class DefaultAspectHandler extends AbstractAspectHandler {

	@Override
	public void before(AspectParameterStore store) {
		// do - nothing
	}
	
	@Override
	public void after(AspectParameterStore store) {
		// do - nothing
	}

	@Override
	public void afterReturning(AspectParameterStore store) {
		// do - nothing
	}

	@Override
	public void afterThrowing(AspectParameterStore store) {
		// do - nothing
	}
	
}
