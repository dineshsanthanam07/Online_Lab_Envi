package com.proctor.service.enrollment.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table(name = "Course_Enrollment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseEnrollment {
    @Id
    @Column("course_enroll_id")
    private UUID courseEnrollId;

    @Column("course_id")
    private String courseId;

    @Column("roll_no")
    private Long rollNo;

    @Column("status")
    private String status ="Pending approval"; // e.g.,

}
