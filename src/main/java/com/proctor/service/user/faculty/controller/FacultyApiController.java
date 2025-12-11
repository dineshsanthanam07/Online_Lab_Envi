package com.proctor.service.user.faculty.controller;

import com.proctor.service.controller.FacultyApi;
import com.proctor.service.dto.FacultyRequestDTO;
import com.proctor.service.dto.FacultyResponseDTO;
import com.proctor.service.user.faculty.service.FacultyService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class FacultyApiController implements FacultyApi {

    private final FacultyService facultyService;

    @Override
    public Mono<ResponseEntity<Void>> deleteFacultyRecord(UUID facultyId, ServerWebExchange exchange) {
        return facultyService.deleteFacultyById(facultyId)
                .thenReturn(ResponseEntity.noContent().build());
    }

    @Override
    public Mono<ResponseEntity<Flux<FacultyResponseDTO>>> getFacultyList(Integer pageNumber, Integer pageSize, UUID facultyId, ServerWebExchange exchange) {
        return Mono.just(ResponseEntity.ok(facultyService.fetchFacultyListOrById(pageNumber, pageSize, facultyId)));
    }

    @Override
    public Mono<ResponseEntity<FacultyResponseDTO>> saveFacultyRecord(Mono<FacultyRequestDTO> facultyRequestDTO, ServerWebExchange exchange) {
        return facultyService.saveAndReturnFaculty(facultyRequestDTO).map(savedEntity -> ResponseEntity.status(HttpStatus.CREATED).body(savedEntity));
    }

    @Override
    public Mono<ResponseEntity<Void>> updateFacultyRecord(Mono<FacultyRequestDTO> facultyRequestDTO, UUID facultyId, ServerWebExchange exchange) {
        return facultyService.updateFaculty(facultyRequestDTO, facultyId).thenReturn(ResponseEntity.accepted().build());
    }
}