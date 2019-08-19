package com.colordung.auth.jwt;

import java.io.UnsupportedEncodingException;

import com.auth0.jwt.algorithms.Algorithm;

public class JwtInfo {
    public static final String HEADER_NAME = "jwt-header";

    public static final String ISSUER = "colordung";

    public static final String TOKEN_KEY = "com.colordung";

    public static final long EXPIRES_LIMIT = 30L;

    public static Algorithm getAlgorithm() {
        try {
            return Algorithm.HMAC256(JwtInfo.TOKEN_KEY);
        } catch (IllegalArgumentException | UnsupportedEncodingException e) {
            return Algorithm.none();
        }
    }
}
