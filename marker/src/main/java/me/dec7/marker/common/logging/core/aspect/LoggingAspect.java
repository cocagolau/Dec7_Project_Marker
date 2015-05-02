package me.dec7.marker.common.logging.core.aspect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.dec7.marker.common.logging.annotation.Loggable;
import me.dec7.marker.common.logging.annotation.Loggable.Status;
import me.dec7.marker.common.logging.core.provider.DefaultLoggingProvider;
import me.dec7.marker.common.logging.core.provider.LoggingProvider;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect implements ApplicationContextAware {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);
	
	private ApplicationContext applicationContext;
	
	@Pointcut(value = "@annotation(annotation)")
//	@Pointcut(value = "execution(* (@me.dec7.marker.common.logging.annotation.Loggable *).*(..))")
	public void log(Loggable annotation) { }
	
//	@Around(value="log(annotation)", argNames="annotation,joinPoint")
//	@Around(value = "execution(* (@me.dec7.marker.common.logging.annotation.Loggable *).*(..)) && args(annotation)")
//	@Around(value = "@annotation(annotation)", argNames="annotation")
//	@Around(value="@annotation(me.dec7.marker.common.logging.annotation.Loggable)")
//	public void around(Loggable annotation, ProceedingJoinPoint joinPoint) {
//	@Around(value = "log(annotation) && args(annotation, ..)", argNames = "annotation, ..")
//	@Around(value = "@annotation(me.dec7.marker.common.logging.annotation.Loggable) && args(annotation)")
	@Around(value = "log(annotation)")
	public Object around(ProceedingJoinPoint joinPoint, Loggable annotation) throws Throwable {
		
		Class<? extends LoggingProvider> clazz = annotation.provider();
		List<Status> statuses = Arrays.asList(annotation.status());
//		List<Status> statuses = new ArrayList<Status>();
//		Class<? extends LoggingProvider> clazz = DefaultLoggingProvider.class;
		final boolean ALL = statuses.contains(Status.ALL);
//		final boolean ALL = true;
		
		Object returnVal = null;
		LoggingProvider provider = null;
		try {
			provider = applicationContext.getBean(clazz);
		} catch(BeansException e) {
		}
		
		try {
			// before status
			if (ALL || statuses.contains(Status.BEFORE)) {
				provider.before();
			}
			
			try {
				returnVal = joinPoint.proceed();
				
			} catch (Throwable e) {
				// afterThrowing status
				if (ALL || statuses.contains(Status.AFTER_THROWING)) {
					provider.afterThrowing();
				}
				throw e;
			} finally {
				// after status
				if (ALL || statuses.contains(Status.AFTER)) {
					provider.after();
				}
			}
			
			// afterReturning status
			if (ALL || statuses.contains(Status.AFTER_RETURNING)) {
				provider.afterReturning();
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		
		return returnVal;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

}
