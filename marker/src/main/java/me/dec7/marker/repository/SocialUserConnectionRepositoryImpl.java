package me.dec7.marker.repository;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.util.MultiValueMap;

public class SocialUserConnectionRepositoryImpl implements ConnectionRepository {
	
	private String userId;
	private SocialUserRepository socialUserRepository;
	private ConnectionFactoryLocator connectionFactoryLocator;
	private TextEncryptor textEncryptor;
	
//	@PostConstruct
//	public void initializeTextEncryptor() {
//		this.textEncryptor = Encryptors.text(encryptionPassword, KeyGenerators.string().generateKey());
//	}

	public SocialUserConnectionRepositoryImpl(String userId, SocialUserRepository socialUserRepository, ConnectionFactoryLocator connectionFactoryLocator, TextEncryptor textEncryptor) {
		this.userId = userId;
		this.socialUserRepository = socialUserRepository;
		this.connectionFactoryLocator = connectionFactoryLocator;
		this.textEncryptor = textEncryptor;
	}

	@Override
	public MultiValueMap<String, Connection<?>> findAllConnections() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Connection<?>> findConnections(String providerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <A> List<Connection<A>> findConnections(Class<A> apiType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MultiValueMap<String, Connection<?>> findConnectionsToUsers(MultiValueMap<String, String> providerUserIds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Connection<?> getConnection(ConnectionKey connectionKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <A> Connection<A> getConnection(Class<A> apiType, String providerUserId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <A> Connection<A> getPrimaryConnection(Class<A> apiType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <A> Connection<A> findPrimaryConnection(Class<A> apiType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addConnection(Connection<?> connection) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateConnection(Connection<?> connection) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeConnections(String providerId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeConnection(ConnectionKey connectionKey) {
		// TODO Auto-generated method stub

	}

}
