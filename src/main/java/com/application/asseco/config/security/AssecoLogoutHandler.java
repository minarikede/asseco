package com.application.asseco.config.security;

import com.application.asseco.service.ActiveJwtService;
import com.application.asseco.web.rest.util.HeaderUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AssecoLogoutHandler implements LogoutHandler {

    private final ActiveJwtService activeJwtService;

    public AssecoLogoutHandler(ActiveJwtService activeJwtService) {
        this.activeJwtService = activeJwtService;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response,
                       Authentication authentication) {
        final String currentJwt = HeaderUtil.getJWTHeader(request);
        activeJwtService.invalidateToken(currentJwt);
    }
}
