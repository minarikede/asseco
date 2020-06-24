package com.application.asseco.service.mapper;

import com.application.asseco.dto.input.UserInputDTO;
import com.application.asseco.dto.output.UserOutputDTO;
import com.application.asseco.model.domain.AppUser;
import com.application.asseco.repository.UserRepository;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

@Mapper(componentModel = "spring")
public abstract class UserMapper extends MapperUsingRepository<UserInputDTO, UserOutputDTO, AppUser> {

    @Autowired
    UserRepository userRepository;

    @Override
    protected JpaRepository<AppUser, Long> getRepository() {
        return userRepository;
    }
}
