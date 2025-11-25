package com.proctor.service.course.controller;

import com.proctor.service.controller.CourseApi;
import com.proctor.service.course.service.CourseApiService;
import com.proctor.service.dto.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CourseApiController implements CourseApi {

    private final CourseApiService courseApiService;

    // Course Endpoints

    @Override
    public Mono<ResponseEntity<Void>> deleteCourseRecord(UUID courseId, ServerWebExchange exchange) {
        return courseApiService.deleteCourseById(courseId).then(Mono.just(ResponseEntity.ok().build()));
    }

    @Override
    public Mono<ResponseEntity<Flux<CourseResponseDTO>>> getCourseList(Integer pageNumber, Integer pageSize, UUID courseId, ServerWebExchange exchange) {
        return Mono.just(ResponseEntity.ok(courseApiService.fetchCourseListBasedOnSelection(courseId, pageNumber, pageSize)));
    }

    @Override
    public Mono<ResponseEntity<CourseResponseDTO>> saveCourseRecord(Mono<CourseRequestDTO> courseRequestDTO, ServerWebExchange exchange) {
        return courseApiService.saveOrUpdateCourse(courseRequestDTO, null).map(ResponseEntity::ok);
    }

    @Override
    public Mono<ResponseEntity<Void>> updateCourseRecord(Mono<CourseRequestDTO> courseRequestDTO, UUID courseId, ServerWebExchange exchange) {
        return courseApiService.saveOrUpdateCourse(courseRequestDTO, courseId).thenReturn(ResponseEntity.ok().build());
    }

    // Course Test Endpoints

    @Override
    public Mono<ResponseEntity<Flux<CourseTestResponseDTO>>> getCourseTestList(UUID courseId, Integer pageNumber, Integer pageSize, UUID testId, ServerWebExchange exchange) {
        return null;
    }

    @Override
    public Mono<ResponseEntity<CourseTestResponseDTO>> saveCourseTestRecord(UUID courseId, Mono<CourseTestRequestDTO> courseTestRequestDTO, ServerWebExchange exchange) {
        return null;
    }

    @Override
    public Mono<ResponseEntity<Void>> updateCourseTestRecord(UUID courseId, Mono<CourseTestRequestDTO> courseTestRequestDTO, UUID testId, ServerWebExchange exchange) {
        return null;
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteCourseTestRecord(UUID courseId, UUID testId, ServerWebExchange exchange) {
        return null;
    }

    // Course test submission endpoints

    @Override
    public Mono<ResponseEntity<Flux<TestSubmissionResponseDTO>>> getTestSubmissionList(UUID testId, UUID courseId, Integer pageNumber, Integer pageSize, UUID submissionId, ServerWebExchange exchange) {
        return null;
    }

    @Override
    public Mono<ResponseEntity<TestSubmissionResponseDTO>> saveTestSubmissionRecord(UUID courseId, UUID testId, Mono<TestSubmissionRequestDTO> testSubmissionRequestDTO, ServerWebExchange exchange) {
        return null;
    }
}