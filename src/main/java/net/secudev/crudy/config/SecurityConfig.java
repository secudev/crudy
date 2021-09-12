package net.secudev.crudy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
@Profile("default")
public class SecurityConfig  extends WebSecurityConfigurerAdapter  {
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		// @formatter:off
		
		auth.inMemoryAuthentication()
	    .withUser("user").password("{noop}password").roles("user").and()
		.withUser("admin").password("{noop}password").roles("admin");
		
		// @formatter:on
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// @formatter:off
		
		http.csrf().disable().authorizeRequests()		
		.antMatchers("/webjars/**").permitAll()
		.anyRequest().authenticated().and().rememberMe().key("uniqueAndSecret").tokenValiditySeconds(86400).and()	
		.formLogin().permitAll()			
		.loginPage("/login").usernameParameter("login")
		.defaultSuccessUrl("/index").and()
		.logout().permitAll();
		// @formatter:on
	}

}
