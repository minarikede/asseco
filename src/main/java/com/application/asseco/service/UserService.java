package com.application.asseco.service;

import com.application.asseco.web.rest.error.UserNotFoundException;
import org.springframework.security.core.userdetails.User;

public interface UserService {
    User loadUserByUsername(String username) throws UserNotFoundException;
}
