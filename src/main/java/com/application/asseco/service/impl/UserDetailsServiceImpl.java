package com.application.asseco.service.impl;

import com.application.asseco.model.domain.AppUser;
import com.application.asseco.repository.UserRepository;
import com.application.asseco.service.mapper.AuthMapper;
import com.application.asseco.web.rest.error.UserNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    private final AuthMapper authMapper;

    public UserDetailsServiceImpl(UserRepository userRepository, AuthMapper authMapper) {
        this.userRepository = userRepository;
        this.authMapper = authMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser applicationUser = userRepository.findByUserName(username);
        if (Objects.isNull(applicationUser)) {
            throw new UserNotFoundException();
        }
        return authMapper.mapToUserDetails(applicationUser);
    }
}
