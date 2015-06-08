package me.dec7.marker.repository;

import java.util.List;
import java.util.Set;

import me.dec7.marker.entity.Account;
import me.dec7.marker.entity.SocialUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.util.MultiValueMap;

@Repository
public interface SocialUserRepository extends JpaRepository<Account, Long> {

	List<SocialUser> findByUserId(String userId);

	List<SocialUser> findByUserIdAndProviderId(String userId, String providerId);

	List<SocialUser> findByUserIdAndProviderUserIds(String userId, MultiValueMap<String, String> providerUserIds);

	SocialUser get(String userId, String providerId, String providerUserId);

	List<SocialUser> findPrimaryByUserIdAndProviderId(String userId, String providerId);

	Integer selectMaxRankByUserIdAndProviderId(String userId, String providerId);

	List<String> findUserIdsByProviderIdAndProviderUserId(String providerId, String providerUserId);

	List<String> findUserIdsByProviderIdAndProviderUserIds(String providerId, Set<String> providerUserIds);
	
}
