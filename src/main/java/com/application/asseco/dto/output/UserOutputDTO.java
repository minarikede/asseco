package com.application.asseco.dto.output;

import lombok.Data;

@Data
public class UserOutputDTO implements OutputDTO {
    private Long id;

    private String address;

    private String email;

    private String name;

    private String phone;

    private String userName;
}
