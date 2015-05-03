package me.dec7.marker.web.controller;

import javax.servlet.http.HttpSession;

import me.dec7.marker.common.aspect.annotation.AspectMethod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/login")
public class LoginController {

	private static final Logger log = LoggerFactory.getLogger(LoginController.class);
	private static final String VIEW_PRIFIX = "login/";

//	@Secured("IS_AUTHENTICATED_FULLY")
	@RequestMapping(value = "", method = RequestMethod.GET)
//	@AspectMethod(value="LoginController")
	public String login(Model model, HttpSession session) {
		log.debug("LoginController.login");
		
		model.addAttribute("sessionId", session.getId());
		
		return VIEW_PRIFIX + "login";
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String processLogin(Model model, HttpSession session) {
		model.addAttribute("sessionId", session.getId());
		
		return VIEW_PRIFIX + "login";
	}
	
	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public String loginError(Model model, HttpSession session) {
		model.addAttribute("sessionId", session.getId());
		
		return VIEW_PRIFIX + "loginError";
	}
	
	@RequestMapping(value = "/success", method = RequestMethod.GET)
	public String loginSuccess(Model model, HttpSession session) {
		model.addAttribute("sessionId", session.getId());
		
		return VIEW_PRIFIX + "loginSuccess";
	}
	
//	@Secured("IS_AUTHENTICATED_FULLY")
	@RequestMapping(value = "/duplicate", method = RequestMethod.GET)
	public String loginDuplicate(Model model, HttpSession session) {
		
		model.addAttribute("sessionId", session.getId());
		
		return VIEW_PRIFIX + "loginDuplicate";
	}
	
	
	
	@RequestMapping(value = "/out", method = RequestMethod.GET)
	public String logout(Model model, HttpSession session) {
		UserDetails userDetails = (UserDetails)session.getAttribute("userLoginInfo");
		
		model.addAttribute("sessionId", session.getId());
//		model.addAttribute("email", userDetails.getUsername());
		
		session.invalidate();
		
		return VIEW_PRIFIX + "logout";
	}
	
}