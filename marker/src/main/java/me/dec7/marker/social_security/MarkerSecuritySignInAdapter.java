package me.dec7.marker.social_security;

import javax.annotation.Resource;

import me.dec7.marker.domain.user.SocialUser;
import me.dec7.marker.service.user.SocialUserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;

@Component(value = "signInAdapter")
public class MarkerSecuritySignInAdapter implements SignInAdapter {
	private static Logger log = LoggerFactory.getLogger(MarkerSecuritySignInAdapter.class);

	public final static String SIGN_IN_DETAILS_SESSION_ATTRIBUTE_NAME = "me.dec7.social_security.signInDetails";

	@Resource(name = "socialUserService")
	private SocialUserService socialUserService;

	public String signIn(String localUserId, Connection<?> connection, NativeWebRequest nativeWebRequest) {
		ConnectionKey connectionKey = connection.getKey();
		SocialUser socialUser = socialUserService.findByUserIdAndConnectionKey(localUserId, connectionKey);
		nativeWebRequest.setAttribute(SIGN_IN_DETAILS_SESSION_ATTRIBUTE_NAME, socialUser, RequestAttributes.SCOPE_SESSION);
//		TemporaryAnswer temporaryAnswer = getTemporaryAnswer(nativeWebRequest);
//		return MarkerSecurityAuthenticationFilter.DEFAULT_AUTHENTICATION_URL + "?redirect=" + temporaryAnswer.generateUrl();
		return MarkerSecurityAuthenticationFilter.DEFAULT_AUTHENTICATION_URL;
	}

//	private TemporaryAnswer getTemporaryAnswer(NativeWebRequest nativeWebRequest) {
//		Object value = nativeWebRequest.getAttribute(TemporaryAnswer.TEMPORARY_ANSWER_KEY, RequestAttributes.SCOPE_SESSION);
//		log.debug("Temporary Answer : {}", value);
//		if (value == null) {
//			return TemporaryAnswer.EMPTY_ANSWER;
//		}
//		return (TemporaryAnswer)value;
//	}
}
