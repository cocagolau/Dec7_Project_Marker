package me.dec7.marker.common.aspect.core.template;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.dec7.marker.common.aspect.annotation.AspectParam;

import org.springframework.core.DefaultParameterNameDiscoverer;

public class AspectParameterStore {

	private List<String> targetNames;
	private Method method;
	private Map<String, Object> attributes;
	
	public AspectParameterStore() { }

	public AspectParameterStore(Method joinPointMethod) {;
		this.method = joinPointMethod;
		initMethod(joinPointMethod);
	}

	private void initMethod(Method joinPointMethod) {
		if (joinPointMethod == null) {
			throw new AspectIllegalArgumentException("method가 null");
		}
		
		Map<String, Object> attributes = new HashMap<String, Object>();
		List<String> targetNames = new ArrayList<String>();
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
					String paramName = paramNames[i];
					targetNames.add(paramName);
					attributes.put(paramName, methodParams[i]);

					break;
				}
			}
		}
		
		this.targetNames = targetNames;
		this.attributes = attributes;
	}

	public List<String> getTargetNames() {
		return targetNames;
	}

	public Method getMethod() {
		return method;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}
	
	public Object get(String key) {
		Object result = attributes.get(key);
		
		if (result == null) {
			throw new AspectIllegalArgumentException("존재하는 키가 없습니다.");
		}
		
		return result;
	}


}