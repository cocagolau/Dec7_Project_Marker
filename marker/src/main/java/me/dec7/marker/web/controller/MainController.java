package me.dec7.marker.web.controller;

import me.dec7.marker.common.aspect.annotation.AspectMethod;
import me.dec7.marker.common.aspect.annotation.AspectMethod.State;
import me.dec7.marker.common.aspect.handler.MainControllerLoggingAspectHandler;
import me.dec7.marker.service.MainService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="")
public class MainController {
	
	private static final Logger log = LoggerFactory.getLogger(MainController.class);
	
	@Autowired
	private MainService mainService;

	@RequestMapping(value = {"","/"}, method = RequestMethod.GET)
//	@AspectMethod(
//			state=State.ALL ,
//			handler=MainControllerLoggingHandler.class,
//			value="MainController")
	public String index(String content1, String content2) {
		log.debug("In MainController.index");
		
		mainService.printService(content1, content2);
		
		return "main";
	}

}
