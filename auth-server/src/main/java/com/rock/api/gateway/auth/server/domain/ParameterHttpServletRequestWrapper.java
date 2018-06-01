package com.rock.api.gateway.auth.server.domain;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class ParameterHttpServletRequestWrapper extends HttpServletRequestWrapper {

	public ParameterHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String getParameter(String name) {
		if ("client_id".equals(name)) {
			return super.getParameter("app_key");
		} else if ("client_secret".equals(name)) {
			return super.getParameter("app_secret");
		} else
			return super.getParameter(name);
	}

}
