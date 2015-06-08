package me.dec7.marker.controller.web.signin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/signin")
public class SignInController {
	
	private static final String ACCOUNT_SIGNIN = "account/signin";

	@RequestMapping(value="", method=RequestMethod.GET)
	public String signin() {
		
		return ACCOUNT_SIGNIN;
	}

}
