/*
 * Copyright 2015-2025 APP TEAM. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.interceptor;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.util.WebUtils;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Interceptor - 令牌
 * 
 * @author APP TEAM
 * @version 1.0
 */
public class TokenInterceptor extends HandlerInterceptorAdapter {

	/** "令牌"属性名称 */
	private static final String TOKEN_ATTRIBUTE_NAME = "token";

	/** "令牌"Cookie名称 */
	private static final String TOKEN_COOKIE_NAME = "token";

	/** "令牌"参数名称 */
	private static final String TOKEN_PARAMETER_NAME = "token";

	/** 错误消息 */
	private static final String ERROR_MESSAGE = "Bad or missing token!";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String token = WebUtils.getCookie(request, TOKEN_COOKIE_NAME);
		if (request.getMethod().equalsIgnoreCase("POST")) {
			String requestType = request.getHeader("X-Requested-With");
			if (requestType != null && requestType.equalsIgnoreCase("XMLHttpRequest")) {
				if (token != null && token.equals(request.getHeader(TOKEN_PARAMETER_NAME))) {
					return true;
				} else {
					response.addHeader("tokenStatus", "accessDenied");
				}
			} else {
				String tokenParameter = request.getParameter(TOKEN_PARAMETER_NAME);
				if (token != null && token.equals(tokenParameter)) {
					return true;
				}
			}
			if (token == null) {
				token = UUID.randomUUID().toString();
				WebUtils.addCookie(request, response, TOKEN_COOKIE_NAME, token);
			}
			response.sendError(HttpServletResponse.SC_FORBIDDEN, ERROR_MESSAGE);
			return false;
		} else {
			if (token == null) {
				token = UUID.randomUUID().toString();
				WebUtils.addCookie(request, response, TOKEN_COOKIE_NAME, token);
			}
			request.setAttribute(TOKEN_ATTRIBUTE_NAME, token);
			return true;
		}
	}

}