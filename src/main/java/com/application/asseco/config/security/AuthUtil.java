package com.application.asseco.config.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import static com.application.asseco.config.security.SecurityConstants.ROLE_PREFIX;

@Slf4j
public final class AuthUtil {

    private AuthUtil() {
    }

    public static String convertToRole(String authority) {
        if (StringUtils.isEmpty(authority)) {
            return null;
        }

        String role = authority.toUpperCase();

        if (!role.startsWith(ROLE_PREFIX)) {
            role = ROLE_PREFIX + role;
        }

        return role;
    }

}
