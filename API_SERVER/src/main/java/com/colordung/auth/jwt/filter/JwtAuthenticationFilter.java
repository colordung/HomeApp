package com.colordung.auth.jwt.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.StringUtils;

import com.colordung.auth.jwt.JwtAuthenticationToken;
import com.colordung.auth.jwt.JwtInfo;

public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    Logger log = LoggerFactory.getLogger(this.getClass());

    public JwtAuthenticationFilter(RequestMatcher requestMatcher) {
        super(requestMatcher);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

//		Enumeration<?> headers = request.getHeaderNames();
//
//		log.debug("=============================================");
//		while(headers.hasMoreElements()){
//			String headerName = (String)headers.nextElement();
//			String value = request.getHeader(headerName);
//			log.debug("{}={}", headerName, value);
//		}
//		log.debug("=============================================");


        String token = request.getHeader(JwtInfo.HEADER_NAME);

        if (StringUtils.isEmpty(token)) {
            throw new AccessDeniedException("Not empty Token");
        } else {
            return getAuthenticationManager().authenticate(new JwtAuthenticationToken(token));
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authResult);
        SecurityContextHolder.setContext(context);
        chain.doFilter(request, response);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        getFailureHandler().onAuthenticationFailure(request, response, failed);
    }
}
