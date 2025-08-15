package com.proctor.service.user.student;

import com.proctor.service.controller.StudentApi;
import com.proctor.service.dto.StudentRequestDTO;
import com.proctor.service.dto.StudentResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class StudentApiController implements StudentApi {
    @Override
    public Mono<ResponseEntity<Void>> deleteStudentRecord(Long studentId, ServerWebExchange exchange) {
        return null;
    }

    @Override
    public Mono<ResponseEntity<Flux<StudentResponseDTO>>> getStudentList(Integer pageNumber, Integer pageSize, Long studentId, ServerWebExchange exchange) {
        return null;
    }

    @Override
    public Mono<ResponseEntity<StudentResponseDTO>> saveStudentRecord(Mono<StudentRequestDTO> studentRequestDTO, ServerWebExchange exchange) {
        return null;
    }

    @Override
    public Mono<ResponseEntity<Void>> updateStudentRecord(Mono<StudentRequestDTO> studentRequestDTO, Long studentId, ServerWebExchange exchange) {
        return null;
    }
}