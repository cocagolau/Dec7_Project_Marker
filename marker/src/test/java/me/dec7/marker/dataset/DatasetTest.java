package me.dec7.marker.dataset;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import me.dec7.marker.config.marker.AppConfig;
import me.dec7.marker.entity.Member;
import me.dec7.marker.repository.MemberRepository;

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
@ContextConfiguration(classes = {AppConfig.class})
@TestExecutionListeners({
		DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
@DatabaseSetup("dataset.xml")
public class DatasetTest {
	
	@Autowired
	private MemberRepository repository;
	
	@Test
	//@ExpectedDatabase("expectedData.xml")
	public void findByName_Dec7() {
		Member member = repository.findByName("dec7");
		assertThat(member.getName(), is("dec7"));
	}

}
