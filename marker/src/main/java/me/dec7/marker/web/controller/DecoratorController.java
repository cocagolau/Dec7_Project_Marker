package me.dec7.marker.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/decorators")
public class DecoratorController {
	
	@RequestMapping("/{name}")
	public String deco(@PathVariable("name") String name) {
		
		return "decorators/" + name;
	}

}