package com.example.hotelbooking.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	MyUserDetailsService myUserDetailsService;

	@Bean
	public UserDetailsService userDetailsService() {
		return new MyUserDetailsService();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
					.antMatchers("/", "/home", "/js/**", "/css/**").permitAll()
					.mvcMatchers(HttpMethod.POST, "/rooms/**", "/reservations/**", "/users/**")
					.hasRole("ADMIN")
					.anyRequest()
					.authenticated()
				.and()
					.formLogin()
					.loginPage("/login")
					.permitAll()
				.and()
					.logout()
					.permitAll()
				.and()
					.httpBasic();

		http.exceptionHandling().accessDeniedHandler(accessDeniedHandler());

	}

	@Bean
	public AccessDeniedHandler accessDeniedHandler(){
		return new CustomAccessDeniedHandler();
	}

//...
	/*@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(myUserDetailsService).passwordEncoder(new BCryptPasswordEncoder()).
	}*/
}