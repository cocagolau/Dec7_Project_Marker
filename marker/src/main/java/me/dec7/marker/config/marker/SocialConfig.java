package me.dec7.marker.config.marker;

import javax.annotation.Resource;

import me.dec7.marker.repository.user.SocialUserRepository;
import me.dec7.marker.repository.user.SocialUsersConnectionRepository;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;

@Configuration
@EnableSocial
@ComponentScan(
		basePackages = {
				"**.support.security",
		}
)
public class SocialConfig  extends SocialConfigurerAdapter {
	
	@Resource(name="socialUserRepository")
	private SocialUserRepository socialUserRepository;
	
	
//	@Bean
//	FacebookConnectionFactory facebookConnectionFactory() {
//		FacebookConnectionFactory connectionFactory = new FacebookConnectionFactory("appid", "secretid");
//		
//		return connectionFactory;
//	}
//	
//	@Bean
//	ConnectionFactoryRegistry connectionFactoryRegistry() {
//		ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
//		registry.addConnectionFactory(facebookConnectionFactory());
//		
//		return registry;
//	}
//	
//	@Bean
//	SocialUsersConnectionRepository usersConnectionRepository() {
//		SocialUsersConnectionRepository connectionRepository = new SocialUsersConnectionRepository(
//				socialUserRepository,
//				connectionFactoryRegistry(),
//				textEncryptor()
//			);
//		
//		return connectionRepository;
//	}
	
	@Override
	public void addConnectionFactories(ConnectionFactoryConfigurer cfConfig, Environment env) {
//		cfConfig.addConnectionFactory(new FacebookConnectionFactory(env.getProperty("facebook.appKey"), env.getProperty("facebook.appSecret")));
		cfConfig.addConnectionFactory(new FacebookConnectionFactory("appid", "secretid"));
	}
	
	@Override
	public UserIdSource getUserIdSource() {
		return new UserIdSource() {			
			@Override
			public String getUserId() {
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				if (authentication == null) {
					throw new IllegalStateException("Unable to get a ConnectionRepository: no user signed in");
				}
				return authentication.getName();
			}
		};
	}
	
	@Override
	public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
		SocialUsersConnectionRepository connectionRepository = new SocialUsersConnectionRepository(
				socialUserRepository,
				connectionFactoryLocator,
				Encryptors.noOpText()
			);
		
		return connectionRepository;
	}

//	@Bean
//	public ConnectController connectController(ConnectionFactoryLocator connectionFactoryLocator, ConnectionRepository connectionRepository) {
//		ConnectController connectController = new ConnectController(connectionFactoryLocator, connectionRepository);
//		connectController.addInterceptor(new PostToWallAfterConnectInterceptor());
//		connectController.addInterceptor(new TweetAfterConnectInterceptor());
//		return connectController;
//	}
	
//	@Bean
//	public DisconnectController disconnectController(UsersConnectionRepository usersConnectionRepository, Environment environment) {
//		return new DisconnectController(usersConnectionRepository, environment.getProperty("facebook.appSecret"));
//	}

//	@Bean
//	public ReconnectFilter apiExceptionHandler(UsersConnectionRepository usersConnectionRepository, UserIdSource userIdSource) {
//		return new ReconnectFilter(usersConnectionRepository, userIdSource);
//	}

//	@Bean
//	@Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)
//	public Facebook facebook(ConnectionRepository repository) {
//		Connection<Facebook> connection = repository.findPrimaryConnection(Facebook.class);
//		return connection != null ? connection.getApi() : null;
//	}
//	
//	@Bean
//	@Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)
//	public Twitter twitter(ConnectionRepository repository) {
//		Connection<Twitter> connection = repository.findPrimaryConnection(Twitter.class);
//		return connection != null ? connection.getApi() : null;
//	}
	

}