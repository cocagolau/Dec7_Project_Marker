package me.dec7.marker.service;

import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {
	
	@Async("taskExecutor")
	public Future<Thread> more() {
		return new AsyncResult<Thread>(Thread.currentThread());
	}

}
