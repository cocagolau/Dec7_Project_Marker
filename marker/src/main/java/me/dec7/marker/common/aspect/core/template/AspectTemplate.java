package me.dec7.marker.common.aspect.core.template;

import java.util.Arrays;
import java.util.List;

import me.dec7.marker.common.aspect.annotation.AspectMethod;
import me.dec7.marker.common.aspect.annotation.AspectMethod.State;
import me.dec7.marker.common.aspect.core.handler.AspectHandler;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectTemplate implements ApplicationContextAware {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AspectTemplate.class);
	
	private ApplicationContext applicationContext;
	
	@Pointcut(value = "@annotation(annotation)")
	public void log(AspectMethod annotation) { }

	@Around(value = "log(annotation)")
	public Object around(ProceedingJoinPoint joinPoint, AspectMethod annotation) throws Throwable {
		Object returnVal = null;
		AspectHandler handler = null;
		
		final List<State> states = Arrays.asList(annotation.state());
		final boolean ALL = states.contains(State.ALL);
		final Class<? extends AspectHandler> handlerClazz = annotation.handler();
		handler = applicationContext.getBean(handlerClazz);
		
//		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		AspectParameterStore store = new AspectParameterStore(joinPoint);

		try {
			// before status
			if (ALL || states.contains(State.BEFORE)) {
				handler.before(store);
			}
			
			// exec joinPoint
			try {
				returnVal = joinPoint.proceed();
				
			} catch (Throwable e) {
				// afterThrowing status
				if (ALL || states.contains(State.AFTER_THROWING)) {
					handler.afterThrowing(store);
				}
				throw e;
			} finally {
				// after status
				if (ALL || states.contains(State.AFTER)) {
					handler.after(store);
				}
			}
			
			// afterReturning status
			if (ALL || states.contains(State.AFTER_RETURNING)) {
				handler.afterReturning(store);
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			
			return returnVal;
		}
		
		return returnVal;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

}
