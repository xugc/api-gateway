package com.rock.api.gateway.auth.server.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import com.rock.api.gateway.auth.server.request.ClientRequestParameters;

public class ClientAuthenticationTokenEndpointFilter extends AbstractAuthenticationProcessingFilter {
	
	private boolean allowOnlyPost;

	private ClientRequestParameters clientParameters;

	public ClientAuthenticationTokenEndpointFilter() {
		this("/token");
	}

	protected ClientAuthenticationTokenEndpointFilter(String defaultFilterProcessesUrl) {
		super(defaultFilterProcessesUrl);
	}

	@Override
	public void afterPropertiesSet() {
		super.afterPropertiesSet();
		setAuthenticationFailureHandler(new AuthenticationFailureHandler() {
			public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
					AuthenticationException exception) throws IOException, ServletException {
			}
		});
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		if (allowOnlyPost && !"POST".equalsIgnoreCase(request.getMethod())) {
			throw new HttpRequestMethodNotSupportedException(request.getMethod(), new String[] { "POST" });
		}

		String clientId = request.getParameter(clientParameters.clientIdParameterName());
		String clientSecret = request.getParameter(clientParameters.clientSecretParameterName());

		// If the request is already authenticated we can assume that this
		// filter is not needed
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.isAuthenticated()) {
			return authentication;
		}

		if (clientId == null) {
			throw new BadCredentialsException("用户凭证不存在!");
		}

		if (clientSecret == null) {
			clientSecret = "";
		}

		clientId = clientId.trim();
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(clientId,
				clientSecret);

		return this.getAuthenticationManager().authenticate(authRequest);
	}

}
