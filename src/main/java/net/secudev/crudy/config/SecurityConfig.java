package net.secudev.crudy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import net.secudev.crudy.security.JpaAuthenticationProvider;
import net.secudev.crudy.security.LoggingFailure;
import net.secudev.crudy.security.LoggingSuccess;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
@Profile("default")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private LoggingSuccess loginSuccess;

	@Autowired
	private LoggingFailure loggingFailure;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	private JpaAuthenticationProvider jpaAuth;

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.authenticationProvider(jpaAuth);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// @formatter:off
		
		http.csrf().disable().headers().frameOptions().disable().and() .authorizeRequests()		
		.antMatchers("/webjars/**").permitAll()
		.antMatchers("/img/**").permitAll()
		.anyRequest().authenticated().and().rememberMe().key("uniqueAndSecret").tokenValiditySeconds(86400).and()	
		.formLogin().permitAll()			
		.loginPage("/login").usernameParameter("login")
		.defaultSuccessUrl("/index").successHandler(loginSuccess).failureHandler(loggingFailure).and()
		.logout().permitAll();
		// @formatter:on
	}

}
