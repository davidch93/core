package com.dch.core.security.jwr.endpoint;

import com.dch.core.dto.response.GeneralResponse;
import com.dch.core.dto.response.builder.ResponseBuilder;
import com.dch.core.security.jwr.service.RedisSessionService;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sayid Sidqi
 */
@RestController
public class RevokeTokenEndpoint {

	private final MessageSource messageSource;
	private final RedisSessionService sessionService;
	
	public RevokeTokenEndpoint(MessageSource messageSource, RedisSessionService sessionService) {
		this.messageSource = messageSource;
		this.sessionService = sessionService;
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping(value = "/auth/revoke")
	public GeneralResponse revokeToken() {
		String key = SecurityContextHolder.getContext().getAuthentication().getName();
		sessionService.clearSession(key);
		return new ResponseBuilder(messageSource).setData("OK").build();
	}

}
