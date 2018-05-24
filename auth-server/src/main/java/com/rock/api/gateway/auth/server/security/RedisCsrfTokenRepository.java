package com.rock.api.gateway.auth.server.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;

public class RedisCsrfTokenRepository implements CsrfTokenRepository {

	private static final String DEFAULT_CSRF_PARAMETER_NAME = "primary_key";

	@Override
	public CsrfToken generateToken(HttpServletRequest request) {
		
		return null;
	}

	@Override
	public void saveToken(CsrfToken token, HttpServletRequest request, HttpServletResponse response) {
	}

	@Override
	public CsrfToken loadToken(HttpServletRequest request) {
		request.getParameter(DEFAULT_CSRF_PARAMETER_NAME);
		return null;
	}

}
