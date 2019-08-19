package com.colordung.auth.ajax;

import com.colordung.dao.UserInfoDao;
import com.colordung.auth.UserDetailsImpl;
import com.colordung.domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;



@Component
public class AjaxUserDetailsService implements UserDetailsService {

    @Autowired
    private UserInfoDao userInfoDao;

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserInfo user = userInfoDao.getUser(username);

        if (user == null) {
//			throw new UsernameNotFoundException(username + "라는 사용자가 없습니다.");
            throw new AccessDeniedException("존재하지 않는 사용자입니다. 아이디와 패스워드를 확인해주세요.");
        }

        return new UserDetailsImpl(user, AuthorityUtils.createAuthorityList(user.getUserKind()));
    }
}
