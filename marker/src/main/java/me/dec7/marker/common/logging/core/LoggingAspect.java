package me.dec7.marker.common.logging.core;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	
	private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);
	
	@Pointcut(value = "@annotation(annotation)")
	public void log(Loggable annotation) { }
	
	@Before("log(annotation)")
	public void before(Loggable annotation) {
		log.debug("before logging start");
	}
	
	@After("log(annotation)")
	public void after(Loggable annotation) {
		log.debug("after logging start");
	}
	
	@AfterReturning("log(annotation)")
	public void afterReturning(Loggable annotation) {
		log.debug("after returning logging");
	}
	
	@AfterThrowing("log(annotation)")
	public void afterThrowing(Loggable annotation) {
		log.debug("after throwing logging");
	}

}
