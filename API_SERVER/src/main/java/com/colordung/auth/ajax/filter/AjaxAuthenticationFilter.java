package com.colordung.auth.ajax.filter;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.colordung.auth.UserDetailsImpl;
import com.colordung.domain.UserInfo;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AjaxAuthenticationFilter extends AbstractAuthenticationProcessingFilter {


    Logger log  = LoggerFactory.getLogger(this.getClass());
    private final ObjectMapper objectMapper;

    public AjaxAuthenticationFilter(RequestMatcher requestMatcher, ObjectMapper objectMapper) {
        super(requestMatcher);
        this.objectMapper = objectMapper;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException {
        if (isJson(request)) {
            UserInfo userInfo = objectMapper.readValue(request.getReader(), UserInfo.class);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userInfo.getUserId(), userInfo.getUserPwd() );
            Authentication authentication = getAuthenticationManager().authenticate(authenticationToken);
            UserDetailsImpl UserDetailsImpl = (UserDetailsImpl) authentication.getPrincipal();
//            //login 위치 구분 -colordung
//            if(("backOffice".equals(member.getLoginKind())
//                    || ("10".equals(UserDetailsImpl.getUserKind()) || "99".equals(UserDetailsImpl.getUserKind())))
//                    || ("client".equals(member.getLoginKind())
//                    && ("20".equals(UserDetailsImpl.getUserKind()) || "30".equals(UserDetailsImpl.getUserKind()) || "40".equals(UserDetailsImpl.getUserKind())))) {
                return authentication;
//            }else {
//
//                if("backOffice".equals(member.getLoginKind())) {
//                    throw new AccessDeniedException("광고주는 로그인 할 수 없습니다.");
//                } else {
//                    throw new AccessDeniedException("관리자 또는 대행사는 로그인 할 수 없습니다.");
//                }
//
//            }
        } else {
            throw new AccessDeniedException("아이디와 패스워드를 확인해주세요.");
        }
    }

    private boolean isJson(HttpServletRequest request) {
        boolean returnVal = MediaType.APPLICATION_JSON_VALUE.equals(request.getContentType());

        if(!returnVal) returnVal = MediaType.APPLICATION_JSON_UTF8_VALUE.equalsIgnoreCase(request.getContentType());

        return returnVal;
    }
}
