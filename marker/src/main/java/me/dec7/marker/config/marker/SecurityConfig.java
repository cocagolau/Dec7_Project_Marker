package me.dec7.marker.config.marker;

import me.dec7.marker.social_security.MarkerAuthenticationProvider;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled=true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
//	@Autowired
//	private MarkerAuthenticationProvider markerAuthenticationProvider;
	
//	@Autowired
//	private UserDetailsService userDetailsService;

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/favicon.ico");
		web.ignoring().antMatchers("/images/**");
		web.ignoring().antMatchers("/scripts/**");
		web.ignoring().antMatchers("/sytlesheets/**");
		web.ignoring().antMatchers("/templates/**");
		web.ignoring().antMatchers("/decorators/**");
	}

//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth.authenticationProvider(authenticationProvider());
////		auth.userDetailsService(userDetailsService);
//	}
	
	
	
	@Bean
	AuthenticationProvider authenticationProvider() {

		return new MarkerAuthenticationProvider();
	}
	
	

	@Bean
	PasswordEncoder passwordEncoder() {
		
//		return new ShaPasswordEncoder(256);
		return new StandardPasswordEncoder();
	}

	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		
		return super.authenticationManager();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authenticationProvider(authenticationProvider());
		http
		.formLogin()
			.loginPage("/login")
			.loginProcessingUrl("/login")
			.failureUrl("/login/error")
			.defaultSuccessUrl("/login/success")
			.usernameParameter("email")
			.passwordParameter("password")
		.and().logout()
		.and().sessionManagement()
			.maximumSessions(1)
			.expiredUrl("/login/duplicate");
		
		http.csrf().disable();
//		http.authenticationProvider(markerAuthenticationProvider);
		
		
//		http
//        .authorizeRequests()
//            .anyRequest().hasAuthority("BASIC_PERMISSION");
		
		
//		http.authorizeRequests().antMatchers("/**").access("hasRole('ROLE_USER')").and().formLogin().and().logout();
		
//		http
//        .formLogin()
//        .loginPage("/login")
//        .and()
//        .logout()
//        .logoutSuccessUrl("/");
		
//			.antMatchers("/admin/**").access("hasRole('ROLE_DBA')")
//			.antMatchers("/dba/**").access("hasRole('ROLE_DBA')")
//			.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
//			.antMatchers("/dba/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_DBA')")
//			.and().formLogin().and().logout();
		
			/*
		http
			// access-denied-page: this is the page users will be redirected to when they try to access protected areas.
			.exceptionHandling().accessDeniedPage("/403")
			.and()

			// The intercept-url configuration is where we specify what roles are allowed access to what areas.
			// We specifically force the connection to https for all the pages, although it could be sufficient just on the login page.
			// The access parameter is where the expressions are used to control which roles can access specific areas.
			// One of the most important things is the order of the intercept-urls,
			// the most catch-all type patterns should at the bottom of the list as the matches are executed in the order they are configured below.
			// So /** (anyRequest()) should always be at the bottom of the list.
			.authorizeRequests()
				.antMatchers("/login**").permitAll()
				.antMatchers("/admin/**").hasRole("ADMIN").anyRequest().authenticated()
			.and()
				.requiresChannel().anyRequest().requiresSecure()
			.and()

			// This is where we configure our login form.
			// login-page: the page that contains the login screen
			// login-processing-url: this is the URL to which the login form should be submitted
			// default-target-url: the URL to which the user will be redirected if they login successfully
			// authentication-failure-url: the URL to which the user will be redirected if they fail login
			// username-parameter: the name of the request parameter which contains the username
			// password-parameter: the name of the request parameter which contains the password
			.formLogin()
				.loginPage("/login")
				.loginProcessingUrl("/login.do")
				.defaultSuccessUrl("/")
				.failureUrl("/login?err=1")
				.usernameParameter("username")
				.passwordParameter("password")
			.and()

			// This is where the logout page and process is configured.
			// The logout-url is the URL to send the user to in order to logout, the logout-success-url is
			// where they are taken if the logout is successful, and the delete-cookies and invalidate-session make sure that we clean up after logout
			.logout()
//				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login?out=1")
				.deleteCookies("JSESSIONID")
				.invalidateHttpSession(true)
			.and()

			// The session management is used to ensure the user only has one session.
			// This isn't compulsory but can add some extra security to your application.
			.sessionManagement().invalidSessionUrl("/login?time=1").maximumSessions(1);
			
			*/
	}
}
