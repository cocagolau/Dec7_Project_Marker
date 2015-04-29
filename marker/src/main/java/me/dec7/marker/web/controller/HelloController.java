package me.dec7.marker.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/main")
public class HelloController {

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index() {
		
		return "main";
	}
	
	@RequestMapping(value = { "/hello" }, method = RequestMethod.GET)
	public String welcomePage(Model model) {
		model.addAttribute("title", "Spring Security Hello World");
		model.addAttribute("message", "This is welcome page!");
		
		return "hello";
	}
}
