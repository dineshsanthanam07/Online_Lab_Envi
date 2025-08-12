package com.proctor.service.course.controller;

import com.proctor.service.controller.CourseApi;
import com.proctor.service.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class CourseApiController implements CourseApi {
    @Override
    public Mono<ResponseEntity<Void>> deleteCourseRecord(Long courseId, ServerWebExchange exchange) {
        return null;
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteCourseTestRecord(String courseId, Long testId, ServerWebExchange exchange) {
        return null;
    }

    @Override
    public Mono<ResponseEntity<Flux<CourseResponseDTO>>> getCourseList(Integer pageNumber, Integer pageSize, Long courseId, ServerWebExchange exchange) {
        return null;
    }

    @Override
    public Mono<ResponseEntity<Flux<CourseTestResponseDTO>>> getCourseTestList(String courseId, Integer pageNumber, Integer pageSize, Long testId, ServerWebExchange exchange) {
        return null;
    }

    @Override
    public Mono<ResponseEntity<Flux<TestSubmissionResponseDTO>>> getTestSubmissionList(String testId, String courseId, Integer pageNumber, Integer pageSize, String submissionId, ServerWebExchange exchange) {
        return null;
    }

    @Override
    public Mono<ResponseEntity<CourseResponseDTO>> saveCourseRecord(Mono<CourseRequestDTO> courseRequestDTO, ServerWebExchange exchange) {
        return null;
    }

    @Override
    public Mono<ResponseEntity<CourseTestResponseDTO>> saveCourseTestRecord(String courseId, Mono<CourseTestRequestDTO> courseTestRequestDTO, ServerWebExchange exchange) {
        return null;
    }

    @Override
    public Mono<ResponseEntity<TestSubmissionResponseDTO>> saveTestSubmissionRecord(String courseId, String testId, Mono<TestSubmissionRequestDTO> testSubmissionRequestDTO, ServerWebExchange exchange) {
        return null;
    }

    @Override
    public Mono<ResponseEntity<Void>> updateCourseRecord(Mono<CourseRequestDTO> courseRequestDTO, Long courseId, ServerWebExchange exchange) {
        return null;
    }

    @Override
    public Mono<ResponseEntity<Void>> updateCourseTestRecord(String courseId, Mono<CourseTestRequestDTO> courseTestRequestDTO, Long testId, ServerWebExchange exchange) {
        return null;
    }
}