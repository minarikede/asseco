package com.application.asseco.service.impl;

import com.application.asseco.service.ActiveJwtService;
import org.springframework.stereotype.Service;

@Service
public class ActiveJwtServiceImpl implements ActiveJwtService {


    @Override
    public boolean activeToken(String jwt) {
        return ACTIVE_TOKENS.stream().filter(value -> value.equals(jwt)).findFirst().isPresent();
    }

    @Override
    public void addToken(String newJwt) {
        ACTIVE_TOKENS.add(newJwt);
    }

    @Override
    public void invalidateToken(String jwt) {
        ACTIVE_TOKENS.remove(jwt);
    }
}
