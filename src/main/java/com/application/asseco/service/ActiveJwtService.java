package com.application.asseco.service;

import java.util.ArrayList;
import java.util.List;

public interface ActiveJwtService {
    List<String> ACTIVE_TOKENS = new ArrayList<>();

    boolean activeToken(String jwt);

    void addToken(String newJwt);

    void invalidateToken(String jwt);
}
