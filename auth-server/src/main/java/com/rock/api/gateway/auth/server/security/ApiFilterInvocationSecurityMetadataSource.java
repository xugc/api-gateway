package com.rock.api.gateway.auth.server.security;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 * @ClassName ApiFilterInvocationSecurityMetadataSource
 * @author 徐故成
 * @date 2017年11月29日 下午7:35:15
 * 
 */
public class ApiFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

	private static final Map<RequestMatcher, Collection<ConfigAttribute>> authMap = new HashMap<>();

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) {
		final HttpServletRequest request = ((FilterInvocation) object).getRequest();
		Collection<ConfigAttribute> requestConfigAttribute = new LinkedList<>();
		for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : authMap.entrySet()) {
			if (entry.getKey().matches(request)) {
				requestConfigAttribute.addAll(entry.getValue());
			}
		}
		return requestConfigAttribute;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		Collection<ConfigAttribute> allConfigAttribute = new LinkedList<>();
		for (Collection<ConfigAttribute> col : authMap.values()) {
			allConfigAttribute.addAll(col);
		}
		return allConfigAttribute;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return FilterInvocation.class.isAssignableFrom(clazz);
	}

}
