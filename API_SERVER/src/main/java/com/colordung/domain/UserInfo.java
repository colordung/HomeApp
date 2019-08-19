package com.colordung.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 사용자 모델
 * @author colordung
 *
 */
@EqualsAndHashCode(callSuper=false)
@Data
public class UserInfo {
    private String userId;						/* 사용자아이디 */
    private String userPwd;						/* 사용자비밀번호 */
    private String userName;					/* 사용자이름 */
    private String userKind;					/* 사용자이름 */
}

