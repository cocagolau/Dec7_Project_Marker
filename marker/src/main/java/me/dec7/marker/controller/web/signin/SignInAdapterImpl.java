package me.dec7.marker.controller.web.signin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import me.dec7.marker.entity.User;
import me.dec7.marker.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.web.context.request.NativeWebRequest;

public class SignInAdapterImpl implements SignInAdapter {
	
	private UserService userService; // injected
	private TokenBasedRememberMeServices tokenBasedRememberMeServices; // injected

//	@Override
//	public String signIn(String userId, Connection<?> connection, NativeWebRequest request) {
//		User user = userService.findByUserId(userId);
//		Authentication authentication = SecurityUtil.signInUser(user);
//		// set remember-me cookie
//		tokenBasedRememberMeServices.onLoginSuccess(
//		(HttpServletRequest) request.getNativeRequest(),
//		(HttpServletResponse) request.getNativeResponse(), authentication);
//		
//		return null;
//	}
	

	private final RequestCache requestCache;
	
	public SignInAdapterImpl(RequestCache requestCache) {
		this.requestCache = requestCache;
	}

	
	@Override
	public String signIn(String localUserId, Connection<?> connection, NativeWebRequest request) {
		SignInUtils.signin(localUserId);
		
		HttpServletRequest nativeReq = request.getNativeRequest(HttpServletRequest.class);
		HttpServletResponse nativeRes = request.getNativeResponse(HttpServletResponse.class);
		SavedRequest saved = requestCache.getRequest(nativeReq, nativeRes);
		if (saved == null) {
			return null;
		}
		requestCache.removeRequest(nativeReq, nativeRes);
		removeAutheticationAttributes(nativeReq.getSession(false));
		return saved.getRedirectUrl();
		
	}

		 
	private void removeAutheticationAttributes(HttpSession session) {
		if (session == null) {
			return;
		}
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}

}