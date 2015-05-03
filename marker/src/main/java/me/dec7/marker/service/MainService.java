package me.dec7.marker.service;

import me.dec7.marker.common.aspect.annotation.MarkerAspect;
import me.dec7.marker.common.aspect.annotation.MarkerAspect.State;
import me.dec7.marker.common.aspect.annotation.MarkerAspectParam;
import me.dec7.marker.common.aspect.provider.MainServiceLoggingProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MainService {
	
	private static final Logger log = LoggerFactory.getLogger(MainService.class);
	
	@MarkerAspect(
			state=State.ALL ,
			provider=MainServiceLoggingProvider.class,
			value="MainService")
	public void printService(@MarkerAspectParam String content1, String content2, MainService mainService) {
		log.debug("MainService.printService-content1: " + content1 + ",  content2: " + content2);
	}

}
