package me.dec7.marker.service;

import me.dec7.marker.common.logging.annotation.Loggable;
import me.dec7.marker.common.logging.provider.MainServiceLoggingProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MainService {
	
	private static final Logger log = LoggerFactory.getLogger(MainService.class);
	
	@Loggable(value="hello", provider=MainServiceLoggingProvider.class)
	public void printService() {
		log.debug("MainService.printService");
	}

}
