package me.dec7.marker.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MainService {
	
	private static final Logger log = LoggerFactory.getLogger(MainService.class);
	
	public void printService(String content1, String content2) {
		log.debug("MainService.printService-content1: " + content1 + ",  content2: " + content2);
	}

}
