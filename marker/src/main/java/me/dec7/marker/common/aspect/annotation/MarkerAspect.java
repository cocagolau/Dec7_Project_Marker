package me.dec7.marker.common.aspect.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import me.dec7.marker.common.aspect.core.provider.AspectProvider;
import me.dec7.marker.common.aspect.core.provider.DefaultAspectProvider;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MarkerAspect {

	public enum Status {
		BEFORE, AFTER, AFTER_RETURNING, AFTER_THROWING, ALL;
	}
	
	Status[] status() default Status.ALL ;

	Class<? extends AspectProvider> provider() default DefaultAspectProvider.class;

	String value();

//	String[] targetNames() default "";
}
