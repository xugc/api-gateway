package com.rock.api.gateway.auth.server.controller;

import java.security.Principal;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("authorizationRequest")
public class AuthroizeConfirmController {

	@RequestMapping("/authorize/confirm")
	public String confirm(Map<String, Object> model, Principal principal) {
		return "confirm";
	}
}
