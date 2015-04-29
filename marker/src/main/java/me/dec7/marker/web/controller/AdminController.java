package me.dec7.marker.web.controller;

import java.security.Principal;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/admin")
@Secured("ROLE_ADMIN")
public class AdminController {

	@RequestMapping(value = {"","/"}, method = RequestMethod.GET)
	public String adminPage(Model model) {
		model.addAttribute("title", "Spring Security Hello World");
		model.addAttribute("message", "This is protected page!");
		
		return "admin";
	}
	
//	@Secured({"ROLE_DBA", "ROLE_ADMIN"})
	@RequestMapping(value = "/dba", method = RequestMethod.GET)
	public String dbaPage(Model model) {
		model.addAttribute("title", "Spring Security Hello World");
		model.addAttribute("message", "This is protected page - Database Page!");

		return "admin";
	}
	
}