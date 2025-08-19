package com.proctor.service.enrollment.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "Submissions")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Submissions {

    @Id
    @Column("submission_id")
    private Long submissionId;

    @Column("language_id")
    private int languageId;

    @Column("test_id")
    private Long testId;

    @Column("student_roll_no")
    private Long rollNo;

    @Column("submission_time")
    private String submissionTime;

    @Column("submitted_code")
    private String submittedCode;




}
