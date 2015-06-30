package me.dec7.marker.repository.user;

import java.util.List;

import me.dec7.marker.domain.user.SocialUser;

import org.springframework.util.MultiValueMap;

public class SocialUserRepositoryImpl implements SocialUserRepositoryCustom {
	@Override
	public List<SocialUser> findsByUserIdAndProviderUserIds(String userId, MultiValueMap<String, String> providerUserIds) {
		throw new UnsupportedOperationException("findsByUserIdAndProviderUserIds method not supported!");
	}
}
