package com.application.asseco.web.rest;

import com.application.asseco.dto.input.UserInputDTO;
import com.application.asseco.dto.output.UserOutputDTO;
import com.application.asseco.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.application.asseco.config.security.SecurityConstants.SIGN_UP_URL;

@Slf4j
@RestController
@RequestMapping(SIGN_UP_URL)
public class UserRegistration {

    private final UserServiceImpl userService;

    public UserRegistration(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserOutputDTO> register(@Valid @RequestBody UserInputDTO inputDTO) {
        log.debug("REST request to register user : {}", inputDTO);

        return ResponseEntity.ok(userService.create(inputDTO));
    }
}
