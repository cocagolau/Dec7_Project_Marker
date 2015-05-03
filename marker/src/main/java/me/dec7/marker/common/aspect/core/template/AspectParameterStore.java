package me.dec7.marker.common.aspect.core.template;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

import me.dec7.marker.common.aspect.annotation.AspectParam;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.DefaultParameterNameDiscoverer;

public class AspectParameterStore {

	/*
	 * targetNamesMappedByArgNames 
	 * key: argName
	 * val: targetName
	 */
	private Map<String, String> targetNamesMappedByArgNames;
	private Method method;
	private Map<String, Object> attributes;
	
	public AspectParameterStore() { }

	public AspectParameterStore(ProceedingJoinPoint joinPoint) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		initMethod(signature.getMethod(), joinPoint.getArgs());
	}

	private void initMethod(Method joinPointMethod, final Object[] args) {
		if (joinPointMethod == null) {
			throw new NullPointerException("method가 null");
		}
		
		Map<String, Object> attributes = new HashMap<String, Object>();
		Map<String, String> targetNamesMappedByArgNames = new HashMap<String, String>();
		
		final Parameter[] methodParams = joinPointMethod.getParameters();
		
		// spring에서 제공하는 DefaultParameterNameDiscoverer 사용하여 method의 parameter 이름 가져옴
		DefaultParameterNameDiscoverer discoverer = new DefaultParameterNameDiscoverer();
		String[] paramNames = discoverer.getParameterNames(joinPointMethod);
		
		// MarkerAspectParam annotation이 존재하는 parameter의 index를 저장
		for (int i=0; i<methodParams.length; i++) {
			Parameter param = methodParams[i];
			Annotation[] annotations = param.getAnnotations();
			
			for (Annotation a : annotations) {
				if (AspectParam.class.equals(a.annotationType())) {
					AspectParam aspectParamAnnotation = (AspectParam) a;
					String argName = paramNames[i];
					String targetName = aspectParamAnnotation.value();
					
					if (StringUtils.equals("", targetName)) {
						targetName = argName;
					}
					targetNamesMappedByArgNames.put(argName, targetName);
					attributes.put(targetName, args[i]);

					break;
				}
			}
		}
		
		this.method = joinPointMethod;
		this.targetNamesMappedByArgNames = targetNamesMappedByArgNames;
		this.attributes = attributes;
	}


	public Map<String, String> getTargetNamesMappedByArgNames() {
		return targetNamesMappedByArgNames;
	}

	public Method getMethod() {
		return method;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}
	
	public Object get(String key) throws IllegalAccessException {
		Object result = attributes.get(key);
		
		if (result == null) {
			throw new IllegalAccessException("존재하는 키가 없습니다.");
		}
		
		return result;
	}


}