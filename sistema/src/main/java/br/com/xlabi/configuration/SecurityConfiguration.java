package br.com.xlabi.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("customUserDetailsService")
	UserDetailsService userDetailsService;

	// @Autowired
	// PersistentTokenRepository tokenRepository;

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
				.antMatchers("/assets/**", "/api/**", "/views/**", "/public/**", "/entrar.jsp", "/login", "/entrar",
						"/")
				.permitAll().antMatchers("/**").authenticated().antMatchers("/edit-user-*")
				.access("hasRole('ADMIN') or hasRole('DBA')").and().formLogin().loginPage("/entrar")
				.loginProcessingUrl("/login").usernameParameter("usuario").passwordParameter("senha").and()
				.exceptionHandling().authenticationEntryPoint(new RestAuthenticationEntryPoint());
		;
		// .and()
		// .rememberMe().rememberMeParameter("remember-me").tokenRepository(tokenRepository)
		// .tokenValiditySeconds(86400).and().csrf()
		// .and().exceptionHandling().accessDeniedPage("/Access_Denied");
	}

	@Bean
	public ShaPasswordEncoder passwordEncoder() {
		return new ShaPasswordEncoder(256);
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	// @Bean
	// public PersistentTokenBasedRememberMeServices
	// getPersistentTokenBasedRememberMeServices() {
	// PersistentTokenBasedRememberMeServices tokenBasedservice = new
	// PersistentTokenBasedRememberMeServices(
	// "remember-me", userDetailsService, tokenRepository);
	// return tokenBasedservice;
	// }

	@Bean
	public AuthenticationTrustResolver getAuthenticationTrustResolver() {
		return new AuthenticationTrustResolverImpl();
	}

}
