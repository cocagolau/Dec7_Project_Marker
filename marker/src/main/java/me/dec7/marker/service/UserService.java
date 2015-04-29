package me.dec7.marker.service;

import me.dec7.marker.entity.User;
import me.dec7.marker.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	public User findByEmail(String email) {
		
		return userRepository.findByEmail(email);
		
	}
	

}
