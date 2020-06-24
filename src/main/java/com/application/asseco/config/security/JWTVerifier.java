package com.application.asseco.config.security;

import com.auth0.jwt.interfaces.DecodedJWT;

import javax.servlet.http.HttpServletRequest;

public interface JWTVerifier {
    DecodedJWT verify(HttpServletRequest request);
}
