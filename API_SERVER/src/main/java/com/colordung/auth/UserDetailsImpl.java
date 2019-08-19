package com.colordung.auth;

import java.util.List;

import com.colordung.domain.UserInfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserDetailsImpl extends User {
    /**
     *
     */
    private static final long serialVersionUID = -5101480961529570309L;

    private String userKind;

    public UserDetailsImpl(String id, List<GrantedAuthority> authorities) {
        super(id, "", authorities);
    }

    public UserDetailsImpl(UserInfo user, List<GrantedAuthority> authorities) {
        super(user.getUserId(), user.getUserPwd(), authorities);
        this.userKind = user.getUserKind();
    }

    public String getUserKind() {
        return userKind;
    }

    public void setUserKind(String userKind) {
        this.userKind = userKind;
    }

}