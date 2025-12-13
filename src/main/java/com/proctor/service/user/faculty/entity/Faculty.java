package com.proctor.service.user.faculty.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;


@Table(name = "Faculty")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Faculty {

    @Id
    @Column("faculty_id")
    private UUID facultyId;

    @Column("name")
    private String name;

    @Column("department")
    private String department;

    @Column("email")
    private String email;

    @Column("designation")
    private String designation;

    @Column("user_id")
    private UUID userId;
}
