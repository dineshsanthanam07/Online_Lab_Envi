package com.proctor.service.course.repository;

import com.proctor.service.course.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends R2dbcRepository<Course, String> {
    Page<Course> findAll(Pageable pageable) ;



}
