package com.rock.api.gateway.auth.server.controller;

import java.security.Principal;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes(value = { "authorizationRequest" })
public class AuthroizeConfirmController {

	@RequestMapping("/authorize/confirm")
	public String confirm(Principal principal, Map<String, Object> model,HttpServletRequest request) {
		model.put("appName", principal.getName());
		return "confirm";
	}
}
