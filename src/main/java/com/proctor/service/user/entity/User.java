package com.proctor.service.user.entity;

import lombok.*;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "User")
public class User {

    @Column("user_id")
    private UUID userId;

    @Column("username")
    private String username;

    @Column("password")
    private String password;

    @Column("role")
    private String role;

    @Column("status")
    private String status = "Pending Approval";
}