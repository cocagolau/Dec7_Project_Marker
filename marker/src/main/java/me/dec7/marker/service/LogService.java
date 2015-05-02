package me.dec7.marker.service;

import me.dec7.marker.entity.Log;
import me.dec7.marker.repository.LogRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogService {

	@Autowired
	private LogRepository logRepository;
	
	public void insert(Log log) {
		logRepository.save(log);
	}
	
}
