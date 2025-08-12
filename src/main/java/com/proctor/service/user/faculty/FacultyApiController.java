package com.proctor.service.user.faculty;

import com.proctor.service.controller.FacultyApi;
import com.proctor.service.dto.FacultyRequestDTO;
import com.proctor.service.dto.FacultyResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class FacultyApiController implements FacultyApi {
    @Override
    public Mono<ResponseEntity<Void>> deleteFacultyRecord(Long facultyId, ServerWebExchange exchange) {
        return null;
    }

    @Override
    public Mono<ResponseEntity<Flux<FacultyResponseDTO>>> getFacultyList(Integer pageNumber, Integer pageSize, Long facultyId, ServerWebExchange exchange) {
        return null;
    }

    @Override
    public Mono<ResponseEntity<FacultyResponseDTO>> saveFacultyRecord(Mono<FacultyRequestDTO> facultyRequestDTO, ServerWebExchange exchange) {
        return null;
    }

    @Override
    public Mono<ResponseEntity<Void>> updateFacultyRecord(Mono<FacultyRequestDTO> facultyRequestDTO, Long facultyId, ServerWebExchange exchange) {
        return null;
    }
}