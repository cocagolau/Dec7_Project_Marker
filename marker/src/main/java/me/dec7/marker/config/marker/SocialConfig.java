package me.dec7.marker.config.marker;

import javax.sql.DataSource;

import me.dec7.marker.controller.web.signin.SignInAdapterImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.connect.web.ProviderSignInController;
import org.springframework.social.connect.web.ReconnectFilter;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;

@Configuration
@EnableSocial
public class SocialConfig implements SocialConfigurer {
	
	private static final String SOCIAL_FACEBOOK_APP_KEY = "social.facebook.app-key";
	private static final String SOCIAL_FACEBOOK_APP_SECRET = "social.facebook.app-secret";
	
	@Autowired
	private DataSource dataSource;
	
	@Override
	public void addConnectionFactories(ConnectionFactoryConfigurer connectionFactoryConfigurer, Environment env) {
		connectionFactoryConfigurer.addConnectionFactory(new FacebookConnectionFactory(env.getProperty(SOCIAL_FACEBOOK_APP_KEY), env.getProperty(SOCIAL_FACEBOOK_APP_SECRET)));
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
		
		return new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
	}
	
	//
	// API Binding Beans
	//
	
	@Bean
	@Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)
	public Facebook facebook(ConnectionRepository repository) {
		Connection<Facebook> connection = repository.findPrimaryConnection(Facebook.class);
		return connection != null ? connection.getApi() : null;
	}
	
	
	
	//
	// Web Controller and Filter Beans
	//
	@Bean
	public ConnectController connectController(ConnectionFactoryLocator connectionFactoryLocator, ConnectionRepository connectionRepository) {
		ConnectController connectController = new ConnectController(connectionFactoryLocator, connectionRepository);
//		connectController.addInterceptor(new PostToWallAfterConnectInterceptor());
//		connectController.addInterceptor(new TweetAfterConnectInterceptor());
		return connectController;
	}

	@Bean
	public ProviderSignInController providerSignInController(ConnectionFactoryLocator connectionFactoryLocator, UsersConnectionRepository usersConnectionRepository) {
		return new ProviderSignInController(connectionFactoryLocator, usersConnectionRepository, new SignInAdapterImpl(new HttpSessionRequestCache()));
	}
	
//	@Bean
//	public DisconnectController disconnectController(UsersConnectionRepository usersConnectionRepository, Environment env) {
//		return new DisconnectController(usersConnectionRepository, env.getProperty("facebook.appSecret"));
//	}

//	@Bean
//	public ReconnectFilter apiExceptionHandler(UsersConnectionRepository usersConnectionRepository, UserIdSource userIdSource) {
//		return new ReconnectFilter(usersConnectionRepository, userIdSource);
//	}
}

//
//<bean id="connectionFactoryLocator"
//class="org.springframework.social.connect.support.ConnectionFactoryRegistry">
//<property name="connectionFactories">
//	<list>
//		<bean
//			class="org.springframework.social.facebook.connect.FacebookConnectionFactory">
//			<constructor-arg value="당신의 app ID" />
//			<constructor-arg value="당신의 앱 시크릿 코드" />
//		</bean>
//	</list>
//</property>
//</bean>