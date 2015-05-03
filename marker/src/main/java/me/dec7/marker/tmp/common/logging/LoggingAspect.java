package me.dec7.marker.tmp.common.logging;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.dec7.marker.common.aspect.annotation.AspectMethod;
import me.dec7.marker.common.aspect.annotation.AspectMethod.State;
import me.dec7.marker.common.aspect.core.handler.AspectHandler;
import me.dec7.marker.common.aspect.core.handler.DefaultAspectProvider;

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

//@Aspect
//@Component
public class LoggingAspect implements ApplicationContextAware {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);
	
	private ApplicationContext applicationContext;
	
	@Pointcut(value = "@annotation(annotation)")
//	@Pointcut(value = "execution(* (@me.dec7.marker.common.logging.annotation.Loggable *).*(..))")
	public void log(AspectMethod annotation) { }
	
//	@Around(value="log(annotation)", argNames="annotation,joinPoint")
//	@Around(value = "execution(* (@me.dec7.marker.common.logging.annotation.Loggable *).*(..)) && args(annotation)")
//	@Around(value = "@annotation(annotation)", argNames="annotation")
//	@Around(value="@annotation(me.dec7.marker.common.logging.annotation.Loggable)")
//	public void around(Loggable annotation, ProceedingJoinPoint joinPoint) {
//	@Around(value = "log(annotation) && args(annotation, ..)", argNames = "annotation, ..")
//	@Around(value = "@annotation(me.dec7.marker.common.logging.annotation.Loggable) && args(annotation)")
	@Around(value = "log(annotation)")
	public Object around(ProceedingJoinPoint joinPoint, AspectMethod annotation) throws Throwable {
		
		Class<? extends AspectHandler> clazz = annotation.handler();
		List<State> statuses = Arrays.asList(annotation.state());
//		List<Status> statuses = new ArrayList<Status>();
//		Class<? extends LoggingProvider> clazz = DefaultLoggingProvider.class;
		final boolean ALL = statuses.contains(State.ALL);
//		final boolean ALL = true;
		
		Object returnVal = null;
		AspectHandler provider = null;
		try {
			provider = applicationContext.getBean(clazz);
		} catch(BeansException e) {
		}
		
		try {
			// before status
			if (ALL || statuses.contains(State.BEFORE)) {
//				provider.before();
			}
			
			try {
				returnVal = joinPoint.proceed();
				
			} catch (Throwable e) {
				// afterThrowing status
				if (ALL || statuses.contains(State.AFTER_THROWING)) {
//					provider.afterThrowing();
				}
				throw e;
			} finally {
				// after status
				if (ALL || statuses.contains(State.AFTER)) {
//					provider.after();
				}
			}
			
			// afterReturning status
			if (ALL || statuses.contains(State.AFTER_RETURNING)) {
//				provider.afterReturning();
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		
		return returnVal;
	}
	
//	@Before("log(annotation)")
//	public void before(Loggable annotation) {
//		Class<? extends LoggingProvider> clazz = annotation.provider();
//		Status[] positions = annotation.status();
//		
//		LoggingProvider provider = applicationContext.getBean(clazz);
//		
//		provider.before();
//	}
//	
//	@After("log(annotation)")
//	public void after(Loggable annotation) {
//		Class<? extends LoggingProvider> clazz = annotation.provider();
//		LoggingProvider provider = applicationContext.getBean(clazz);
//		
//		provider.afterReturning();
//	}
//	
//	@AfterReturning("log(annotation)")
//	public void afterReturning(Loggable annotation) {
//		Class<? extends LoggingProvider> clazz = annotation.provider();
//		LoggingProvider provider = applicationContext.getBean(clazz);
//		
//		provider.afterReturning();
//	}
//	
//	@AfterThrowing("log(annotation)")
//	public void afterThrowing(Loggable annotation) {
//		Class<? extends LoggingProvider> clazz = annotation.provider();
//		LoggingProvider provider = applicationContext.getBean(clazz);
//		
//		provider.afterThrowing();
//	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

}
