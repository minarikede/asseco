package com.application.asseco.model.domain;

import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "user_role")
public class UserRole {

    @EmbeddedId
    private UserRolePk id;
}
