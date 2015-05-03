package me.dec7.marker.common.aspect.core.template;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.dec7.marker.common.aspect.annotation.AspectMethod;
import me.dec7.marker.common.aspect.annotation.AspectMethod.State;
import me.dec7.marker.common.aspect.core.handler.AspectHandler;

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
public class AspectTemplate implements ApplicationContextAware {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AspectTemplate.class);
	
	private ApplicationContext applicationContext;
	
	@Pointcut(value = "@annotation(annotation)")
	public void log(AspectMethod annotation) { }

	@Around(value = "log(annotation)")
	public Object around(ProceedingJoinPoint joinPoint, AspectMethod annotation) throws Throwable {
		Object returnVal = null;
		List<AspectHandler> handlers = new ArrayList<AspectHandler>();
		
		final List<State> states = Arrays.asList(annotation.state());
		final boolean ALL = states.contains(State.ALL);
		final Class<? extends AspectHandler>[] handlerClasses = annotation.handlers();
		
		for (int i=0; i<handlerClasses.length; i++) {
			handlers.add(applicationContext.getBean(handlerClasses[i]));
		}

		AspectParameterStore store = new AspectParameterStore(joinPoint, annotation.value());

		try {
			// before status
			if (ALL || states.contains(State.BEFORE)) {
				execHandlers(State.BEFORE, handlers, store);
			}
			
			// exec joinPoint
			try {
				returnVal = joinPoint.proceed();
				
			} catch (Throwable e) {
				// afterThrowing status
				if (ALL || states.contains(State.AFTER_THROWING)) {
					execHandlers(State.AFTER_THROWING, handlers, store);
				}
				throw e;
			} finally {
				// after status
				if (ALL || states.contains(State.AFTER)) {
					execHandlers(State.AFTER, handlers, store);
				}
			}
			
			// afterReturning status
			if (ALL || states.contains(State.AFTER_RETURNING)) {
				execHandlers(State.AFTER_RETURNING, handlers, store);
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			
			return returnVal;
		}
		
		return returnVal;
	}

	private void execHandlers(State state, List<AspectHandler> handlers, AspectParameterStore store) {
		
		for (AspectHandler handler : handlers) {
			switch(state) {
				case BEFORE :
					handler.before(store);
					break;
				case AFTER :
					handler.after(store);
					break;
				case AFTER_RETURNING :
					handler.afterReturning(store);
					break;
				case AFTER_THROWING :
					handler.afterThrowing(store);
					break;
				default :
					LOGGER.warn("[" + this.getClass().getName() +"] [AspectHandler] not found handler state");
					break;
			}
		}
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

}
