package com.application.asseco.config.security.impl;

import com.application.asseco.config.security.JWTVerifier;
import com.application.asseco.service.ActiveJwtService;
import com.application.asseco.web.rest.util.HeaderUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

import static com.application.asseco.config.security.SecurityConstants.*;

@Service
public class JWTVerifierImpl implements JWTVerifier {

    private final ActiveJwtService activeJwtService;

    public JWTVerifierImpl(ActiveJwtService activeJwtService) {
        this.activeJwtService = activeJwtService;
    }

    @Override
    public DecodedJWT verify(HttpServletRequest request) {
        String token = HeaderUtil.getJWTHeader(request);

        if (StringUtils.isEmpty(token)) {
            throw new JWTVerificationException(AUTHORIZATION_HEADER + " header value is not present!");
        }

        if (!activeJwtService.activeToken(token)) {
            throw new JWTVerificationException("The token is not in the active token store!");
        }

        return JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                .build()
                .verify(token.replace(TOKEN_PREFIX, ""));
    }
}
