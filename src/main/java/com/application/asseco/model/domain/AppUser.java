package com.application.asseco.model.domain;

import com.application.asseco.enums.Role;
import com.application.asseco.enums.UserType;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.List;

@Entity
@Data
@Table(name = "user")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "active")
    private boolean active;

    @Size(max = 128)
    @Column(name = "address")
    private String address;

    @JoinColumn(name = "created", insertable = false, updatable = false)
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private AppUser created;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "deleted")
    private boolean deleted;

    @Column(name = "deleted_at")
    private Instant deletedAt;

    @Column(name = "deleted_flag")
    private boolean deletedFlag;

    @NotNull
    @Size(max = 128)
    @Column(name = "email")
    private String email;

    @Size(max = 256)
    @Column(name = "email_token")
    private String emailToken;

    @Column(name = "last_login")
    private Instant lastLogin;

    @NotNull
    @Size(max = 128)
    @Column(name = "name")
    private String name;

    @Column(name = "next_login_change_pwd")
    private Boolean nextLoginChangePwd;

    @NotNull
    @Size(max = 256)
    @Column(name = "password")
    private String password;

    @Column(name = "password_expired")
    private Boolean passwordExpired;

    @Size(max = 256)
    @Column(name = "phone")
    private String phone;

    @Column(name = "settlement_id")
    private Long settlementId;

    @Size(max = 256)
    @Column(name = "temp_password")
    private String tempPassword;

    @Column(name = "temp_password_expired")
    private Boolean tempPasswordExpired;

    @JoinColumn(name = "updated_by", insertable = false, updatable = false)
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private AppUser updatedBy;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @Column(name = "user_type")
    private UserType userType;

    @Size(max = 256)
    @Column(name = "user_name")
    private String userName;

    @Column(name = "settlements_by_settlement_id")
    private String settlementsBySettlementId;

    @JoinColumn(name = "user_by_created_id", insertable = false, updatable = false)
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private AppUser userByCreatedId;

    @JoinColumn(name = "user_by_deleted_id", insertable = false, updatable = false)
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private AppUser userByDeletedId;

    @JoinColumn(name = "user_by_updated_id", insertable = false, updatable = false)
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private AppUser userByUpdatedId;

    @OneToMany(targetEntity = UserRole.class, mappedBy = "id.role", fetch = FetchType.EAGER)
    private List<Role> roles;

}
