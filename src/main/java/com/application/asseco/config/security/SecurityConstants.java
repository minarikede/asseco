package com.application.asseco.config.security;

public final class SecurityConstants {

    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String SIGN_UP_URL = "/register";
    public static final String LOGIN_URL = "/login";
    public static final String RIGHTS = "Rights";
    public static final String ROLE_PREFIX = "ROLE_";

    private SecurityConstants() {
    }
}
