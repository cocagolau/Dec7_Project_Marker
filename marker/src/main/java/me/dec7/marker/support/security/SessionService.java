package me.dec7.marker.support.security;

import javax.annotation.Resource;

import me.dec7.marker.common.enums.ProviderType;
import me.dec7.marker.domain.user.SocialUser;
import me.dec7.marker.service.user.SocialUserService;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * 로그인 사용자를 관리한다.
 */
@Service("sessionService")
public class SessionService {
	@Resource(name = "socialUserService")
	private SocialUserService socialUserService;

	public SocialUser getLoginUser() {
		if (!isAuthenticated()) {
			return SocialUser.GUEST_USER;
		}

		SocialUser socialUser;
		if (isSlippUser()) {
			socialUser = socialUserService.findByEmail(getAuthenticatedUserName());
		} else {
			socialUser = socialUserService.findByUserId(getAuthenticatedUserName());
		}
		if (socialUser == null) {
			return SocialUser.GUEST_USER;
		}
		return socialUser;
	}

	public boolean isAuthenticated() {
		return getAuthentication() == null ? false : getAuthentication().isAuthenticated();
	}

	public boolean isSlippUser() {
		Authentication authentication = getAuthentication();
		Object details = authentication.getDetails();
		if (!(details instanceof ProviderType)) {
			return false;
		}

		ProviderType providerType = (ProviderType)details;
		if (providerType == ProviderType.Marker) {
			return true;
		}

		return false;
	}

	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	private String getAuthenticatedUserName() {
		return getAuthentication() == null ? null : getAuthentication().getName();
	}
}
