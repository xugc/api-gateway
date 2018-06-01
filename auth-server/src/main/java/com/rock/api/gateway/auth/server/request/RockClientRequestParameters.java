package com.rock.api.gateway.auth.server.request;

public class RockClientRequestParameters implements ClientRequestParameters {

	@Override
	public String clientIdParameterName() {
		return null;
	}

	@Override
	public String clientSecretParameterName() {
		return null;
	}

}
