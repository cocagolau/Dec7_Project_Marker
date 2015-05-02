package me.dec7.marker.common.logging.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import me.dec7.marker.common.logging.core.provider.DefaultLoggingProvider;
import me.dec7.marker.common.logging.core.provider.LoggingProvider;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Loggable {

	public enum Status {
		BEFORE, AFTER, AFTER_RETURNING, AFTER_THROWING, ALL;
	}
	
	Status[] status() default Status.ALL ;

	Class<? extends LoggingProvider> provider() default DefaultLoggingProvider.class;

	String value();
}
