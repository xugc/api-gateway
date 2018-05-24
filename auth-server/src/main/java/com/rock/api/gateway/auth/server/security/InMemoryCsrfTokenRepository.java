package com.rock.api.gateway.auth.server.security;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;

public class InMemoryCsrfTokenRepository implements CsrfTokenRepository {

	private static final Map<String, String> TOKENS = new HashMap<>();

	@Override
	public CsrfToken generateToken(HttpServletRequest request) {
		return null;
	}

	@Override
	public void saveToken(CsrfToken token, HttpServletRequest request, HttpServletResponse response) {
	}

	@Override
	public CsrfToken loadToken(HttpServletRequest request) {
		return null;
	}

}
