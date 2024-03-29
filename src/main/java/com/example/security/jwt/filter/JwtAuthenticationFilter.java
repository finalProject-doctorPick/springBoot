package com.example.security.jwt.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.security.jwt.exception.JwtExceptionCode;
import com.example.security.jwt.token.JwtAuthenticationToken;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final AuthenticationManager authenticationManager;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = "";
		try {
			token = getToken(request);
			if (StringUtils.hasText(token)) {
				getAuthentication(token);
			}
			filterChain.doFilter(request, response);
		} catch (NullPointerException | IllegalStateException e) {
			request.setAttribute("exception", JwtExceptionCode.NOT_FOUND_TOKEN.getCode());
			log.error("Not found Token // token : {}", token);
			log.error("Set Request Exception Code : {}", request.getAttribute("exception"));
			throw new BadCredentialsException("throw new not found token exception");
		} catch (SecurityException | MalformedJwtException e) {
			request.setAttribute("exception", JwtExceptionCode.INVALID_TOKEN.getCode());
			log.error("Invalid Token // token : {}", token);
			log.error("Set Request Exception Code : {}", request.getAttribute("exception"));
			throw new BadCredentialsException("throw new invalid token exception");
		} catch (ExpiredJwtException e) {
			request.setAttribute("exception", JwtExceptionCode.EXPIRED_TOKEN.getCode());
			log.error("EXPIRED Token // token : {}", token);
			log.error("Set Request Exception Code : {}", request.getAttribute("exception"));
			throw new BadCredentialsException("throw new expired token exception");
		} catch (UnsupportedJwtException e) {
			request.setAttribute("exception", JwtExceptionCode.UNSUPPORTED_TOKEN.getCode());
			log.error("Unsupported Token // token : {}", token);
			log.error("Set Request Exception Code : {}", request.getAttribute("exception"));
			throw new BadCredentialsException("throw new unsupported token exception");
		} catch (BadCredentialsException e) {
			log.error("인증 실패: {}", e.getMessage());
            logger.error("암호가 잘못되었습니다. 토큰 확인에 실패했습니다.", e);
            // 필요에 따라 예외를 처리하거나 다시 던집니다.
            throw e;
        } catch (Exception e) {
			log.error("====================================================");
			log.error("JwtFilter - doFilterInternal() 오류 발생", e);
			log.error("token : {}", token);
			log.error("Exception Message : {}", e.getMessage());
			log.error("Exception StackTrace : {");
			e.printStackTrace();
			log.error("}");
			log.error("====================================================");
			throw new BadCredentialsException("throw new exception");
		}
	}

	private void getAuthentication(String token) {
		JwtAuthenticationToken authenticationToken = new JwtAuthenticationToken(token);
		Authentication authenticate = authenticationManager.authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authenticate);
	}

	private String getToken(HttpServletRequest request) {
	    String authorization = request.getHeader("Authorization");
	    if (StringUtils.hasText(authorization) && authorization.startsWith("Bearer ")) {
	        return authorization.replace("Bearer ", "");
	    }
	    return null;
	}
}