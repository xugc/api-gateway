package com.rock.api.gateway.auth.server.configuration;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import com.rock.api.gateway.auth.server.filter.ClientAuthenticationTokenEndpointFilter;

@Configuration
@EnableAuthorizationServer
public class AuthorizeServerConfiguration extends AuthorizationServerConfigurerAdapter {

	@Autowired
	DataSource dataSource;
	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.passwordEncoder(new BCryptPasswordEncoder());
		security.addTokenEndpointAuthenticationFilter(new ClientAuthenticationTokenEndpointFilter());
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.jdbc(dataSource);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(new JdbcTokenStore(dataSource)).authenticationManager(authenticationManager)
				.getFrameworkEndpointHandlerMapping().setMappings(customPathMap());
		endpoints.approvalStore(new JdbcApprovalStore(dataSource));
	}

	private Map<String, String> customPathMap() {
		Map<String, String> customPathMap = new HashMap<>();
		customPathMap.put("/oauth/authorize", "/authorize");
		customPathMap.put("/oauth/confirm_access", "/authorize/confirm");
		customPathMap.put("/oauth/token", "/token");
		customPathMap.put("/oauth/check_token", "/token/check");
		return customPathMap;
	}

}
