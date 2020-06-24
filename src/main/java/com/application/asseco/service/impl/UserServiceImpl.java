package com.application.asseco.service.impl;

import com.application.asseco.criteria.UserCriteria;
import com.application.asseco.dto.input.UserInputDTO;
import com.application.asseco.dto.output.UserOutputDTO;
import com.application.asseco.enums.Role;
import com.application.asseco.model.domain.AppUser;
import com.application.asseco.repository.UserRepository;
import com.application.asseco.service.CrudService;
import com.application.asseco.service.mapper.UserMapper;
import com.application.asseco.web.rest.error.UserNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Objects;

@Service
@Transactional
public class UserServiceImpl implements CrudService<UserOutputDTO, UserInputDTO, UserCriteria> {
    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    private final UserQueryService userQueryService;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder, UserQueryService userQueryService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.userQueryService = userQueryService;
    }


    @Override
    public UserOutputDTO create(UserInputDTO userInputDTO) {
        AppUser user = userMapper.toEntityForCreate(userInputDTO);
        user.setPassword(passwordEncoder.encode(userInputDTO.getPassword()));
        user.setRoles(Arrays.asList(Role.PUBLIC));
        AppUser persistedUser = userRepository.save(user);
        return userMapper.toOutputDto(persistedUser);
    }


    @Override
    public UserOutputDTO update(Long id, UserInputDTO userInputDTO) {
        return userMapper.toOutputDto(userRepository.save(userMapper.toEntityForUpdate(id, userInputDTO)));
    }

    @Override
    public UserOutputDTO getOne(Long id) {
        AppUser user = userRepository.findById(id).get();
        if (Objects.isNull(user))
            throw new UserNotFoundException();
        return userMapper.toOutputDto(user);
    }

    @Override
    public Page<UserOutputDTO> findAll(UserCriteria userCriteria, Pageable pageable) {
        Page<AppUser> appUsers = userQueryService.find(userCriteria, pageable);
        return new PageImpl<>(userMapper.toOutputDto(appUsers.getContent()), pageable, appUsers.getTotalPages());
    }

    @Override
    public Void delete(Long id) {
        userRepository.delete(userRepository.getOne(id));

        return null;
    }

}
