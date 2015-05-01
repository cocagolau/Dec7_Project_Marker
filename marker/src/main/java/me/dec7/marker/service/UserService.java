package me.dec7.marker.service;

import me.dec7.marker.common.logging.core.Loggable;
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

	@Loggable
	public User findByEmail(String email) {
		log.debug("UserServiceImpl.findByEmail");
		
		return userRepository.findByEmail(email);
		
	}
	

}
