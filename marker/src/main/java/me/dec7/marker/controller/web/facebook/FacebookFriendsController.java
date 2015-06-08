package me.dec7.marker.controller.web.facebook;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//@Controller
public class FacebookFriendsController {

	@Autowired
	private Facebook facebook;

	@RequestMapping(value="/facebook/friends", method=RequestMethod.GET)
	public String showFeed(Model model) {
		model.addAttribute("friends", facebook.friendOperations().getFriendProfiles());
		return "facebook/friends";
	}
	
}
