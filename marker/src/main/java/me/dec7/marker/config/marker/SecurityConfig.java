package me.dec7.marker.config.marker;

import javax.sql.DataSource;

import me.dec7.marker.support.security.MarkerAuthenticationProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled=true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
//	@Autowired
//	private MarkerAuthenticationProvider markerAuthenticationProvider;
	
//	@Autowired
//	private UserDetailsService userDetailsService;
	
	@Autowired
	private DataSource dataSource;

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/favicon.ico");
		web.ignoring().antMatchers("/images/**");
		web.ignoring().antMatchers("/scripts/**");
		web.ignoring().antMatchers("/sytlesheets/**");
		web.ignoring().antMatchers("/templates/**");
		web.ignoring().antMatchers("/decorators/**");
	}
	
	
	@Bean
	AuthenticationProvider authenticationProvider() {

		return new MarkerAuthenticationProvider();
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
//			.loginPage("/signin")
//			.loginProcessingUrl("/signin/authenticate")
//			.failureUrl("/signin?param.error=bad_credentials")
			.defaultSuccessUrl("/login/success")
			.usernameParameter("email")
			.passwordParameter("password")
		.and().logout()
			.logoutUrl("/logout")
			.deleteCookies("JSESSIONID")
		.and().authorizeRequests()
			.antMatchers("/admin/**", "/favicon.ico", "/resources/**", "/auth/**", "/signin/**", "/signup/**", "/disconnect/facebook").permitAll()
			.antMatchers("/**").authenticated()
		.and().rememberMe()
		.and().sessionManagement()
			.maximumSessions(1)
			.expiredUrl("/login/duplicate");
		
		http.csrf().disable();
	}

	
	
	
	
	
//	@Autowired
//	public void registerAuthentication(AuthenticationManagerBuilder auth) throws Exception {
//		auth.jdbcAuthentication()
//				.dataSource(dataSource)
//				.usersByUsernameQuery("select username, password, true from Account where username = ?")
//				.authoritiesByUsernameQuery("select username, 'ROLE_USER' from Account where username = ?")
//				.passwordEncoder(passwordEncoder());
//	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Bean
	public TextEncryptor textEncryptor() {
		return Encryptors.noOpText();
	}
}
