package me.dec7.marker.service;

import me.dec7.marker.common.aspect.annotation.AspectMethod;
import me.dec7.marker.common.aspect.annotation.AspectMethod.State;
import me.dec7.marker.common.aspect.annotation.AspectParam;
import me.dec7.marker.common.aspect.handler.MainServiceLoggingAspectHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MainService {
	
	private static final Logger log = LoggerFactory.getLogger(MainService.class);
	
//	@AspectMethod(
//			state=State.ALL ,
//			handler=MainServiceLoggingHandler.class,
//			value="MainService")
	public void printService(@AspectParam String content1, String content2) {
		log.debug("MainService.printService-content1: " + content1 + ",  content2: " + content2);
	}

}
