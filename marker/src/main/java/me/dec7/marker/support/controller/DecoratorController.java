package me.dec7.marker.support.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/decorators")
public class DecoratorController {
	
	private static final String DECORATORS_URI_PREFIX = "decorators/";

	@RequestMapping("/{name}")
	public String deco(@PathVariable("name") String name) {
		
		return DECORATORS_URI_PREFIX + name;
	}

}