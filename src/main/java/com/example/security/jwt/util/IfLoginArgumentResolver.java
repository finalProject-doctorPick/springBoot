package com.example.security.jwt.util;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.example.security.jwt.token.JwtAuthenticationToken;

public class IfLoginArgumentResolver implements HandlerMethodArgumentResolver {
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.getParameterAnnotation(IfLogin.class) != null
				&& parameter.getParameterType() == LoginUserDto.class;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		Authentication authentication = null;
		try {
			authentication = SecurityContextHolder.getContext().getAuthentication();
		} catch (Exception ex) {
			return null;
		}
		if (authentication == null) {
			return null;
		}

		JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) authentication;
		LoginUserDto loginUserDto = new LoginUserDto();
		Object principal = jwtAuthenticationToken.getPrincipal(); // email
		if (principal == null)
			return null;
		loginUserDto.setEmail((String) principal);

		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
		while (iterator.hasNext()) {
			GrantedAuthority grantedAuthority = iterator.next();
			String role = grantedAuthority.getAuthority();
			loginUserDto.addRole(role);
		}

		return loginUserDto;
	}
}
