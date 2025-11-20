package com.proctor.service.user.student;

import com.proctor.service.controller.StudentApi;
import com.proctor.service.dto.StudentRequestDTO;
import com.proctor.service.dto.StudentResponseDTO;
import com.proctor.service.user.student.service.StudentService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class StudentApiController implements StudentApi {

    private final StudentService studentService;
    @Override
    public Mono<ResponseEntity<Void>> deleteStudentRecord(Long studentId, ServerWebExchange exchange) {
        return studentService.deleteStudentById(studentId).thenReturn(ResponseEntity.noContent().build());
    }

    @Override
    public Mono<ResponseEntity<Flux<StudentResponseDTO>>> getStudentList(Integer pageNumber, Integer pageSize, Long studentId, ServerWebExchange exchange) {
        return Mono.just(ResponseEntity.ok(studentService.fetchStudentListOrById(pageNumber,pageSize,studentId)));
    }

    @Override
    public Mono<ResponseEntity<StudentResponseDTO>> saveStudentRecord(Mono<StudentRequestDTO> studentRequestDTO, ServerWebExchange exchange) {
        return studentService.saveAndReturnStudent(studentRequestDTO).map(savedEnity->ResponseEntity.status(HttpStatus.CREATED).body(savedEnity));
    }

    @Override
    public Mono<ResponseEntity<Void>> updateStudentRecord(Mono<StudentRequestDTO> studentRequestDTO, Long studentId, ServerWebExchange exchange) {
        return studentService.updateStudent(studentRequestDTO,studentId).thenReturn(ResponseEntity.accepted().build());
    }
}