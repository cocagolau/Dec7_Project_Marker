package me.dec7.marker.controller.web;

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
	public String index(String content1, String content2) {
		log.debug("In MainController.index");
		
		mainService.printService(content1, content2);
		
		return "main";
	}

}
