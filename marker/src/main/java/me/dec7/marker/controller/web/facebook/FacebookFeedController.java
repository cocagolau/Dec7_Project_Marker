package me.dec7.marker.controller.web.facebook;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//@Controller
public class FacebookFeedController {

	@Autowired
	private Facebook facebook;

	@RequestMapping(value="/facebook/feed", method=RequestMethod.GET)
	public String showFeed(Model model) {
		PagedList<Post> feed = facebook.feedOperations().getFeed();
		
		System.out.println("----> " + feed.size());
		
		model.addAttribute("feed", feed);
		return "facebook/feed";
	}
	
	@RequestMapping(value="/facebook/feed", method=RequestMethod.POST)
	public String postUpdate(String message) {
		facebook.feedOperations().updateStatus(message);
		return "redirect:/facebook/feed";
	}
	
}
