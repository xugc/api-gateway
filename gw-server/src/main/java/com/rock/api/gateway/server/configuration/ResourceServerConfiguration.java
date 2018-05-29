package com.rock.api.gateway.server.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import com.rock.api.gateway.server.security.RockAccessDecisionManager;
import com.rock.api.gateway.server.security.RockFilterInvocationSecurityMetadataSource;

@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
	@Autowired
	DataSource dataSource;
	@Autowired
	AuthenticationManager authenticationManagerBean;

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(new JdbcTokenStore(dataSource));
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		FilterSecurityInterceptor securityInterceptor = new FilterSecurityInterceptor();
		securityInterceptor.setSecurityMetadataSource(new RockFilterInvocationSecurityMetadataSource());
		securityInterceptor.setAccessDecisionManager(new RockAccessDecisionManager());
		securityInterceptor.setAuthenticationManager(authenticationManagerBean);
		securityInterceptor.afterPropertiesSet();
		http.requestMatchers().anyRequest().and().addFilterAt(securityInterceptor, FilterSecurityInterceptor.class);
	}

}
