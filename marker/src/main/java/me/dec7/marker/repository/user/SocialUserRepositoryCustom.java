package me.dec7.marker.repository.user;

import java.util.List;

import me.dec7.marker.domain.user.SocialUser;

import org.springframework.util.MultiValueMap;

public interface SocialUserRepositoryCustom {
	
	List<SocialUser> findsByUserIdAndProviderUserIds(String userId, MultiValueMap<String, String> providerUserIds);
}
