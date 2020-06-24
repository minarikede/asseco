package com.application.asseco.dto.input;

import lombok.Data;

@Data
public class UserInputDTO implements InputDTO {
    private String address;

    private String email;

    private String name;

    private String password;

    private String phone;

    private String userName;

}
