package com.colordung.dao;

import com.colordung.domain.UserInfo;

public interface UserInfoDao {
	UserInfo getUser(String userId);
	int getDuplicationUserIdCheck(String id);
	int addUser(UserInfo userInfo);
}
