package com.proctor.service.enrollment.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Table(name = "Question")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    @Id
    @Column("question_id")
    private Long questionId;

    @Column("title")
    private String title;

    @Column("questions")
    private String questions;

    @Column("test_id")
    private Long testId;


}
