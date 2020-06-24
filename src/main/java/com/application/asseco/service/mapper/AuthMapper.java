package com.application.asseco.service.mapper;

import com.application.asseco.config.security.AuthUtil;
import com.application.asseco.config.security.impl.AppUserDetails;
import com.application.asseco.model.domain.AppUser;
import org.mapstruct.Mapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class AuthMapper {

    public AppUserDetails mapToUserDetails(AppUser auth) {
        List<GrantedAuthority> authorityList;

        if (!CollectionUtils.isEmpty(auth.getRoles())) {
            final List<String> authorities = auth.getRoles().stream().map(role -> role.name()).collect(Collectors.toList());
            final String[] objects = authorities.stream().map(s -> AuthUtil.convertToRole(s)).toArray(String[]::new);
            authorityList = AuthorityUtils.createAuthorityList(objects);
        } else {
            authorityList = new ArrayList<>();
        }

        return new AppUserDetails(auth.getId(), auth.getUserName(), auth.getPassword(), authorityList);
    }

}
