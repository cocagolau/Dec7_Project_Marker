package me.dec7.marker.web.controller;

import me.dec7.marker.common.logging.annotation.Loggable;
import me.dec7.marker.common.logging.annotation.Loggable.Status;
import me.dec7.marker.common.logging.provider.MainControllerLoggingProvider;
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
	@Loggable(status=Status.ALL , value="MainController", provider=MainControllerLoggingProvider.class)
	public String index() {
		log.debug("In MainController.index");
		
		mainService.printService();
		
		return "main";
	}

}
