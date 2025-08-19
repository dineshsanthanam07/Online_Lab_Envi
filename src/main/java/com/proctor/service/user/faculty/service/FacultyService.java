package com.proctor.service.user.faculty.service;

import com.proctor.service.dto.FacultyRequestDTO;
import com.proctor.service.dto.FacultyResponseDTO;
import com.proctor.service.user.faculty.repository.FacultyRepository;
import com.proctor.service.user.faculty.entity.Faculty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class FacultyService {

    private final FacultyRepository facultyRepository;

    public Mono<Void> deleteFacultyById(Long facultyId) {
        return facultyRepository.deleteById(facultyId)
                .doOnSuccess(voidType -> log.atInfo().log("Deleted faculty Record successfully"));
    }

    public Flux<FacultyResponseDTO> fetchFacultyListOrById(Integer pageNumber, Integer pageSize, Long facultyId) {
        return Mono.justOrEmpty(facultyId)
                .flatMap(facultyRepository::findById)
                .flux()
                .doOnNext(faculty -> log.atInfo().log("Returning Faculty record for requested facultyId"))
                .switchIfEmpty(
                        subscriber -> facultyRepository.findAll(Sort.by(Sort.Order.asc("facultyId")))
                                .skip((pageNumber - 1L) * pageSize)
                                .take(pageSize)
                                .doOnComplete(() -> log.atInfo().log("Fetched Faculty list"))
                ).map(
                        facultyRecord -> new FacultyResponseDTO()
                                .id(facultyRecord.getFacultyId())
                                .name(facultyRecord.getName())
                                .department(facultyRecord.getDepartment())
                                .username(facultyRecord.getEmail()) // TODO Correct this statement
                                .designation(facultyRecord.getDesignation())
                                .email(facultyRecord.getEmail())
                );
    }

    public Mono<FacultyResponseDTO> saveAndReturnFaculty(Mono<FacultyRequestDTO> facultyRequestDTO) {
        return facultyRequestDTO
                .map(
                        dto -> {
                            Faculty facultyEntity = new Faculty();
                            facultyEntity.setFacultyId(dto.getId());
                            facultyEntity.setName(dto.getName());
                            facultyEntity.setDepartment(dto.getDepartment());
                            // TODO set User ID and Password
                            facultyEntity.setDesignation(dto.getDesignation());
                            facultyEntity.setEmail(dto.getEmail());
                            return facultyEntity;
                        }
                ).flatMap(facultyRepository::save)
                .map(
                        facultyRecord -> new FacultyResponseDTO()
                                .id(facultyRecord.getFacultyId())
                                .name(facultyRecord.getName())
                                .department(facultyRecord.getDepartment())
                                .username(facultyRecord.getEmail()) //TODO Correct this statement
                                .designation(facultyRecord.getDesignation())
                                .email(facultyRecord.getEmail())
                ).doOnSuccess(savedEntity -> log.atInfo().log("Saved faculty record successfully"));
    }

    public Mono<Void> updateFaculty(Mono<FacultyRequestDTO> facultyRequestDTOMono, Long facultyId) {
        return facultyRequestDTOMono.doOnNext(
                facultyRequestDTO -> {
                    facultyRepository.findById(facultyId)
                            .map(
                                    fetchedEntity -> {
                                        fetchedEntity.setName(facultyRequestDTO.getName());
                                        fetchedEntity.setDepartment(facultyRequestDTO.getDepartment());
                                        fetchedEntity.setEmail(facultyRequestDTO.getEmail());
                                        fetchedEntity.setDesignation(facultyRequestDTO.getDesignation());
                                        return fetchedEntity;
                                    }
                            ).doOnNext(facultyRepository::save);
                }
        ).then();
    }
}