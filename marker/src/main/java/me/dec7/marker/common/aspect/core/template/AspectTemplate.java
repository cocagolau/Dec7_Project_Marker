package me.dec7.marker.common.aspect.core.template;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.dec7.marker.common.aspect.annotation.MarkerAspect;
import me.dec7.marker.common.aspect.annotation.MarkerAspect.State;
import me.dec7.marker.common.aspect.annotation.MarkerAspectParam;
import me.dec7.marker.common.aspect.core.AspectParameter;
import me.dec7.marker.common.aspect.core.provider.AspectProvider;

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
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectTemplate implements ApplicationContextAware {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AspectTemplate.class);
	
	private ApplicationContext applicationContext;
	
	@Pointcut(value = "@annotation(annotation)")
	public void log(MarkerAspect annotation) { }

	@Around(value = "log(annotation)")
	public Object around(ProceedingJoinPoint joinPoint, MarkerAspect annotation) throws Throwable {
		Object returnVal = null;
		AspectProvider provider = null;
		
		final Class<? extends AspectProvider> clazz = annotation.provider();
		provider = applicationContext.getBean(clazz);
		
		
//		final Class<? extends AspectProvider> clazz = annotation.provider();
//		provider = applicationContext.getBean(clazz);
		
		final List<State> states = Arrays.asList(annotation.state());
		final boolean ALL = states.contains(State.ALL);
//		final Map<String, Object> attributes = new HashMap<String, Object>();
//		final List<String> targetNames = new ArrayList<String>();
		
		// parameters' name of a method
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		AspectParameterStore store = new AspectParameterStore(signature.getMethod());
		
//		Parameter[] parameters = method.getParameters();
//		Class<?>[] parameterTypes = method.getParameterTypes();
		
		
		
//		// spring에서 제공하는 DefaultParameterNameDiscoverer 사용하여 method의 parameter 이름 가져옴
//		DefaultParameterNameDiscoverer discoverer = new DefaultParameterNameDiscoverer();
//		String[] paramNames = discoverer.getParameterNames(method);
//		
//		// MarkerAspectParam annotation이 존재하는 parameter의 index를 저장
//		for (int i=0; i<parameters.length; i++) {
//			Parameter param = parameters[i];
//			Annotation[] annotations = param.getAnnotations();
//			
//			for (Annotation a : annotations) {
//				if (MarkerAspectParam.class.equals(a.annotationType())) {
//					String paramName = paramNames[i];
//					targetNames.add(paramName);
////					attributes.put(paramName, parameters[i]);
//
//					break;
//				}
//			}
//		}

		try {
			// before status
			if (ALL || states.contains(State.BEFORE)) {
				provider.before(store);
			}
			
			// exec joinPoint
			try {
				returnVal = joinPoint.proceed();
				
			} catch (Throwable e) {
				// afterThrowing status
				if (ALL || states.contains(State.AFTER_THROWING)) {
					provider.afterThrowing(store);
				}
				throw e;
			} finally {
				// after status
				if (ALL || states.contains(State.AFTER)) {
					provider.after(store);
				}
			}
			
			// afterReturning status
			if (ALL || states.contains(State.AFTER_RETURNING)) {
				provider.afterReturning(store);
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
