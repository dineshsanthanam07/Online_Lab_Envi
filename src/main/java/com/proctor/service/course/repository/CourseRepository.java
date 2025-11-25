package com.proctor.service.course.repository;

import com.proctor.service.course.entity.Course;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CourseRepository extends R2dbcRepository<Course, UUID> {}