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
import me.dec7.marker.common.aspect.annotation.MarkerAspect.Status;
import me.dec7.marker.common.aspect.annotation.MarkerAspectParam;
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
		
		Class<? extends AspectProvider> clazz = annotation.provider();
		List<Status> statuses = Arrays.asList(annotation.status());
//		List<String> targetNames = Arrays.asList(annotation.targetNames());
		final boolean ALL = statuses.contains(Status.ALL);
		
		Map<String, Object> attributes = new HashMap<String, Object>();
		
		// parameters' name of a method
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		Parameter[] parameters = method.getParameters();
		
		// spring에서 제공하는 DefaultParameterNameDiscoverer 사용하여 method의 parameter 이름 가져옴
		DefaultParameterNameDiscoverer discoverer = new DefaultParameterNameDiscoverer();
		String[] paramNames = discoverer.getParameterNames(method);
		
		// MarkerAspectParam annotation이 존재하는 parameter의 index를 저장
		List<String> targetNames = new ArrayList<String>();
		for (int i=0; i<parameters.length; i++) {
			Parameter param = parameters[i];
			Annotation[] annotations = param.getAnnotations();
			
			for (Annotation a : annotations) {
				if (MarkerAspectParam.class.equals(a.annotationType())) {
					String paramName = paramNames[i];
					targetNames.add(paramName);
					attributes.put(paramName, parameters[i]);
					break;
				}
			}
		}
		
		try {
			provider = applicationContext.getBean(clazz);

			try {
				// before status
				if (ALL || statuses.contains(Status.BEFORE)) {
					provider.before(attributes);
				}
				
				// exec joinPoint
				try {
					returnVal = joinPoint.proceed();
					
				} catch (Throwable e) {
					// afterThrowing status
					if (ALL || statuses.contains(Status.AFTER_THROWING)) {
						provider.afterThrowing(attributes);
					}
					throw e;
				} finally {
					// after status
					if (ALL || statuses.contains(Status.AFTER)) {
						provider.after(attributes);
					}
				}
				
				// afterReturning status
				if (ALL || statuses.contains(Status.AFTER_RETURNING)) {
					provider.afterReturning(attributes);
				}

			} catch (Exception e) {
				LOGGER.error(e.getMessage());
			}
			
		} catch(BeansException e) {
			LOGGER.error(e.getMessage());
		}
		
		return returnVal;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

}
