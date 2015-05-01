package me.dec7.marker.common.logging.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Loggable {

	LoggablePosition position() default LoggablePosition.BEFORE;

	public enum LoggablePosition {
		BEFORE, AFTER;
	};
}
