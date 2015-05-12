package me.dec7.marker.common.aspect.core.template;

import static org.junit.Assert.fail;

import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AspectTemplateMockTest {
	
	@Mock
	private ProceedingJoinPoint pjp;

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
