package me.dec7.marker.tmp.service;

import me.dec7.marker.entity.User;
import me.dec7.marker.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SecurityUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserService userService;
	
	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userService.findByEmail(userName);
		
		if (user == null) {
			throw new UsernameNotFoundException("UserName(email) " + userName + " not found");
		}
		return user;
	}
}
