package com.talentica.web.security;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.talentica.web.model.User;
import org.apache.commons.io.IOUtils;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AppAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	private static final String ERROR_MESSAGE = "Server Error Bro!";

	private final ObjectMapper objectMapper = new ObjectMapper();


	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		{
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(request.getParameter("userName"), request.getParameter("password"));
			setDetails(request, token);

			return this.getAuthenticationManager().authenticate(token);

		}
	}
}
