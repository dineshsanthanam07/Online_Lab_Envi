package com.proctor.service.enrollment.controller;

import com.proctor.service.controller.EnrollmentApi;
import com.proctor.service.dto.EnrollmentRequestDTO;
import com.proctor.service.dto.EnrollmentResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class EnrollmentApiController implements EnrollmentApi {
    @Override
    public Mono<ResponseEntity<Void>> deleteEnrollmentRecord(Long enrollmentId, ServerWebExchange exchange) {
        return null;
    }

    @Override
    public Mono<ResponseEntity<Flux<EnrollmentResponseDTO>>> getEnrollmentList(Integer pageNumber, Integer pageSize, Long enrollmentId, ServerWebExchange exchange) {
        return null;
    }

    @Override
    public Mono<ResponseEntity<EnrollmentResponseDTO>> saveEnrollmentRecord(Mono<EnrollmentRequestDTO> enrollmentRequestDTO, ServerWebExchange exchange) {
        return null;
    }

    @Override
    public Mono<ResponseEntity<Void>> updateEnrollmentRecord(Mono<EnrollmentRequestDTO> enrollmentRequestDTO, Long enrollmentId, ServerWebExchange exchange) {
        return null;
    }
}