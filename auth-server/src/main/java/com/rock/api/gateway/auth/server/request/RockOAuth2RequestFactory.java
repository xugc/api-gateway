package com.rock.api.gateway.auth.server.request;

import java.util.Map;

import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.TokenRequest;

public class RockOAuth2RequestFactory implements OAuth2RequestFactory {
	
	private ClientRequestParameters clientParameters;

	@Override
	public AuthorizationRequest createAuthorizationRequest(Map<String, String> authorizationParameters) {
		return null;
	}

	@Override
	public OAuth2Request createOAuth2Request(AuthorizationRequest request) {
		return null;
	}

	@Override
	public OAuth2Request createOAuth2Request(ClientDetails client, TokenRequest tokenRequest) {
		return null;
	}

	@Override
	public TokenRequest createTokenRequest(Map<String, String> requestParameters, ClientDetails authenticatedClient) {
		return null;
	}

	@Override
	public TokenRequest createTokenRequest(AuthorizationRequest authorizationRequest, String grantType) {
		return null;
	}

}
