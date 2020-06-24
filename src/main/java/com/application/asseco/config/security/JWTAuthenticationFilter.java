package com.application.asseco.config.security;

import com.application.asseco.config.security.impl.AppUserDetails;
import com.application.asseco.model.domain.AppUser;
import com.application.asseco.service.ActiveJwtService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.CollectionUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import static com.application.asseco.config.security.SecurityConstants.*;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    private final ActiveJwtService activeJwtService;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, ActiveJwtService activeJwtService) {
        this.authenticationManager = authenticationManager;
        this.activeJwtService = activeJwtService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            AppUser appUser = new ObjectMapper()
                    .readValue(req.getInputStream(), AppUser.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            appUser.getUserName(),
                            appUser.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

        final AppUserDetails userDetails = (AppUserDetails) auth.getPrincipal();

        String token = JWT.create()
                .withSubject(((User) auth.getPrincipal()).getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .withClaim(RIGHTS, getRights(userDetails))
                .sign(Algorithm.HMAC512(SECRET.getBytes()));

        activeJwtService.addToken(token);

        res.addHeader(AUTHORIZATION_HEADER, TOKEN_PREFIX + token);
    }

    private String getRights(AppUserDetails userDetails) {
        String rights = "";

        final Collection<GrantedAuthority> authorities = userDetails.getAuthorities();
        if (!CollectionUtils.isEmpty(authorities)) {
            rights = authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors
                    .joining(","));
        }
        return rights;
    }
}
