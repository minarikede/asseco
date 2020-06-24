package com.application.asseco.model.domain;

import com.application.asseco.enums.Role;
import lombok.Builder;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Embeddable
@Builder
public class UserRolePk implements Serializable {
    @OneToOne
    @JoinColumn(name = "user_id")
    private AppUser user;


    @Column(name = "role")
    private Role role;


}
