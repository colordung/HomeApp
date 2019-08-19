package com.colordung.service;

import com.colordung.dao.UserInfoDao;
import com.colordung.domain.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserInfoDao userInfoDao;

    public UserInfo saveMember(UserInfo userInfo) {
        userInfo.setUserPwd(passwordEncoder.encode(userInfo.getUserPwd()));
        userInfoDao.addUser(userInfo);

        return userInfo;
    }

    public UserInfo getMember(String id){

        UserInfo userInfo =  userInfoDao.getUser(id);

        return userInfo;
    }

	public int getDuplicationUserIdCheck(String id) {
		
		int usercount =  userInfoDao.getDuplicationUserIdCheck(id);

        return usercount;
	}

}
