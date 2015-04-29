package me.dec7.marker.web.controller;

import static org.junit.Assert.*;
import me.dec7.marker.config.marker.AppConfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class}, loader=AnnotationConfigContextLoader.class)
public class MainControllerSpringTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
