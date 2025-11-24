package com.proctor.service.enrollment.repository;

import com.proctor.service.dto.EnrollmentResponseDTO;
import com.proctor.service.enrollment.entity.CourseEnrollment;
import org.reactivestreams.Publisher;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;

public interface EnrollmentRepository extends R2dbcRepository<CourseEnrollment,Long> {


    Flux<CourseEnrollment> findCourseEnrollmentByCourseId(String courseId);
}
