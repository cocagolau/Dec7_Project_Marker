package me.dec7.marker.service;

import me.dec7.marker.common.aspect.annotation.AspectMethod;
import me.dec7.marker.common.aspect.annotation.AspectMethod.State;
import me.dec7.marker.common.aspect.handler.MainControllerLoggingHandler;
import me.dec7.marker.common.aspect.handler.MainServiceLoggingHandler;
import me.dec7.marker.entity.User;
import me.dec7.marker.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	private static final Logger log = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository userRepository;

	public User findByEmail(String email) {
		log.debug("UserServiceImpl.findByEmail");
		
		return userRepository.findByEmail(email);
		
	}
	

}
