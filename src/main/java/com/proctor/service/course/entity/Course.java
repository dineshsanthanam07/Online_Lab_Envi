package com.proctor.service.course.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "Courses")
public class Course {

    @Id
    @Column("course_id")
    private UUID courseId;
    @Column("course_name")
    private String courseName;
    @Column("created_at")
    private OffsetDateTime createdAt;
    @Column("faculty_id")
    private Long facultyId;
}