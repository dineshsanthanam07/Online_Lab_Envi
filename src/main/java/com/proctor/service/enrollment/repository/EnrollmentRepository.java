package com.proctor.service.enrollment.repository;

import com.proctor.service.enrollment.entity.CourseEnrollment;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface EnrollmentRepository extends R2dbcRepository<CourseEnrollment, UUID> {


    Flux<CourseEnrollment> findCourseEnrollmentByCourseId(String courseId);
}
