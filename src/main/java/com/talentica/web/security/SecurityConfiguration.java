package com.talentica.web.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.talentica.web.controller.LoginController;
import com.talentica.web.model.User;
import com.talentica.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.Http401AuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserService userService;
	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private LoginController loginController;

	@Bean
	public AppAuthenticationFilter authenticationFilter() throws Exception {
		AppAuthenticationFilter appAuthenticationFilter = new AppAuthenticationFilter();
		appAuthenticationFilter.setAuthenticationSuccessHandler(myAuthenticationSuccessHandler());
		//appAuthenticationFilter.setAuthenticationSuccessHandler(this::loginSuccessHandler);
		appAuthenticationFilter.setAuthenticationFailureHandler(this::loginFailureHandler);
		appAuthenticationFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/home/login", "POST"));
		appAuthenticationFilter.setAuthenticationManager(authenticationManagerBean());
		return appAuthenticationFilter;
	}

	@Bean
	public AuthenticationSuccessHandler myAuthenticationSuccessHandler(){
		return new LoginSuccessHandler();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests()
			.antMatchers("/home").permitAll()
			.antMatchers("/home/login").authenticated()

			.and()
			.addFilterBefore(
				authenticationFilter(),
				UsernamePasswordAuthenticationFilter.class)
			.logout()
			.logoutUrl("/logout")
			.logoutSuccessHandler(this::logoutSuccessHandler)

			.and()
			.exceptionHandling()
			.authenticationEntryPoint(new Http401AuthenticationEntryPoint("401"));
	}


	private void loginSuccessHandler(
		HttpServletRequest request,
		HttpServletResponse response,
		Authentication authentication) throws IOException {
		User loggedInUser = userService.findByUsername(authentication.getName())
			.orElseThrow(() -> new UsernameNotFoundException("User not found: " + authentication.getName()));
		//response.sendRedirect("/home/login");
		response.sendRedirect("/welcomeAdmin/"+loggedInUser.getId());
		response.setStatus(HttpStatus.OK.value());
		objectMapper.writeValue(response.getWriter(), loggedInUser);
	}

	private void loginFailureHandler(
		HttpServletRequest request,
		HttpServletResponse response,
		AuthenticationException e) throws IOException {

		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		objectMapper.writeValue(response.getWriter(), "Nooooo");
	}

	private void logoutSuccessHandler(
		HttpServletRequest request,
		HttpServletResponse response,
		Authentication authentication) throws IOException {

		response.setStatus(HttpStatus.OK.value());
		objectMapper.writeValue(response.getWriter(), "Bye!");
	}

}
