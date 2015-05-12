package me.dec7.marker.web.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import me.dec7.marker.service.MainService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(MockitoJUnitRunner.class)
public class MainControllerMockTest {
	
	private static final String WORLD = "world";

	private static final String HELLO = "hello";

	@InjectMocks
	private MainController mainController;
	
	@Spy
//	@Mock
	private MainService mainService;
	
	@Captor
	private ArgumentCaptor<String> args;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		// Process mock annotations
		MockitoAnnotations.initMocks(this);

		// Setup Spring test in standalone mode
		this.mockMvc = MockMvcBuilders.standaloneSetup(mainController).build();
	}
	
	@Test
	public void index() throws Exception {

		this.mockMvc.perform(get("/")
				.param("content1", HELLO)
				.param("content2", WORLD))
				.andExpect(status().isOk());
//				.andExpect(forwardedUrl(IndexController.PAGE_INDEX))
//				.andExpect(model().attributeExists("page_error"));
				
		verify(mainService).printService(args.capture(), args.capture());
		
		List<String> actualArgs = args.getAllValues();
		assertThat(actualArgs.get(0), equalTo(HELLO));
		assertThat(actualArgs.get(1), equalTo(WORLD));
	
	}

}
