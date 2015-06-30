package me.dec7.marker.social_security;

import java.util.Collection;

import me.dec7.marker.common.enums.ProviderType;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class MarkerUser extends User {
	
	private static final long serialVersionUID = -1300939667852040175L;
	
	private ProviderType providerType;

	public MarkerUser(String username, String password, ProviderType providerType, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		this.providerType = providerType;
	}

	public ProviderType getProviderType() {
		return providerType;
	}
}
