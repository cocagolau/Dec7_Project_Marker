package me.dec7.marker.controller.web;

import me.dec7.marker.service.MainService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/user")
@Secured("ROLE_USER")
public class UserController {
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private MainService mainService;

	@RequestMapping(value = {"/{email}"}, method = RequestMethod.GET)
	public String index(@PathVariable String email, Model model) {
		
		log.debug("email: " + email);
		model.addAttribute("email", email);
		
		return "user";
	}

}
