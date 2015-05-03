package me.dec7.marker.common.aspect.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import me.dec7.marker.common.aspect.core.handler.AspectHandler;
import me.dec7.marker.common.aspect.core.handler.DefaultAspectProvider;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AspectMethod {

	public enum State {
		BEFORE, AFTER, AFTER_RETURNING, AFTER_THROWING, ALL;
	}
	
	State[] state() default State.ALL ;

	Class<? extends AspectHandler> handler() default DefaultAspectProvider.class;

	String value();

}
