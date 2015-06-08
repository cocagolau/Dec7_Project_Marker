package me.dec7.marker.controller.web.signup;

import javax.validation.Valid;

import me.dec7.marker.common.entity.Message;
import me.dec7.marker.common.enums.MessageType;
import me.dec7.marker.common.exception.UsernameAlreadyInUseException;
import me.dec7.marker.common.form.SignupForm;
import me.dec7.marker.controller.web.signin.SignInUtils;
import me.dec7.marker.entity.Account;
import me.dec7.marker.service.AccountService;
import me.dec7.marker.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

@Controller
@RequestMapping(value="/signup")
public class SignUpController {
	
private static final String ACCOUNT_SIGNUP = "account/signup";

//	private final AccountRepository accountRepository;
	private ProviderSignInUtils providerSignInUtils;
	
	@Autowired
//	private UserService userService;
	private AccountService accountService;
	
	
	{
		providerSignInUtils = new ProviderSignInUtils();
	}
	

	
	@RequestMapping(value="", method=RequestMethod.GET)
	public String signupForm(@ModelAttribute("form") SignupForm form, WebRequest request) {
		Connection<?> connection = providerSignInUtils.getConnectionFromSession(request);
		
		if (connection != null) {
			request.setAttribute("message", new Message(MessageType.INFO, "Your " + StringUtils.capitalize(connection.getKey().getProviderId()) + " account is not associated with a Spring Social Showcase account. If you're new, please sign up."), WebRequest.SCOPE_REQUEST);
			form = SignupForm.fromProviderUser(connection.fetchUserProfile());
		}
		
		return ACCOUNT_SIGNUP;
	}

	@RequestMapping(value="", method=RequestMethod.POST)
	public String signup(@Valid SignupForm form, BindingResult formBinding, WebRequest request) {
		if (formBinding.hasErrors()) {
			return null;
		}
		Account account = createAccount(form, formBinding);
		if (account != null) {
			SignInUtils.signin(account.getUsername());
			providerSignInUtils.doPostSignUp(account.getUsername(), request);
			return "redirect:/";
		}
		return null;
	}

	// internal helpers
	
	private Account createAccount(SignupForm form, BindingResult formBinding) {
		try {
			Account account = new Account(form.getUsername(), form.getPassword(), form.getFirstName(), form.getLastName());
			accountService.create(account);
			return account;
		} catch (UsernameAlreadyInUseException e) {
			formBinding.rejectValue("username", "user.duplicateUsername", "already in use");
			return null;
		}
	}
	
}

