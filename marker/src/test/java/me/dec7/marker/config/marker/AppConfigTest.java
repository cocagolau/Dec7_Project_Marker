package me.dec7.marker.config.marker;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Future;

import me.dec7.marker.service.AsyncService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class}, loader=AnnotationConfigContextLoader.class)
public class AppConfigTest {

	
	@Autowired
	private AsyncService asyncService;
	
	
	@Test
	public void async() throws Exception {
		Set<Thread> threads = new HashSet<Thread>();

		for (int i = 0; i < 200; i++) {
			collectThreadInfo(asyncService.more(), threads);
		}
		assertThat(threads.size(), is(10));
		assertThat(threads.contains(asyncService.more().get()), is(true));
	}

	private void collectThreadInfo(Future<Thread> future, Set<Thread> threads) throws Exception {
		threads.add(future.get());
	}
	
	
	

}
