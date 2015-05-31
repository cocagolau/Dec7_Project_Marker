package me.dec7.marker.dataset;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import me.dec7.marker.config.marker.PersistenceConfig;
import me.dec7.marker.entity.User;
import me.dec7.marker.repository.UserRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class})
@TestExecutionListeners({
		DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
public class DatasetTest {
	
	@Autowired
	private UserRepository repository;
	
	@Test
	@DatabaseSetup("classpath:dataset/dataset.xml")
	//@ExpectedDatabase("expectedData.xml")
	public void findByName_Dec7() {
//		User user = repository.findByEmail("dec7");
//		assertThat(user.getName(), is("dec7"));
	}

}
