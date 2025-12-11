package com.proctor.service.user.faculty.service;

import com.proctor.service.dto.FacultyRequestDTO;
import com.proctor.service.dto.FacultyResponseDTO;
import com.proctor.service.user.UserService;
import com.proctor.service.user.entity.User;
import com.proctor.service.user.faculty.repository.FacultyRepository;
import com.proctor.service.user.faculty.entity.Faculty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class FacultyService {

    private final FacultyRepository facultyRepository;
    private final UserService userService;

    public Mono<Void> deleteFacultyById(UUID facultyId) {
        return facultyRepository.deleteById(facultyId)
                .doOnSuccess(voidType -> log.atInfo().log("Deleted faculty Record successfully"));
    }

    public Flux<FacultyResponseDTO> fetchFacultyListOrById(Integer pageNumber, Integer pageSize, UUID facultyId) {
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

    @Transactional
    public Mono<FacultyResponseDTO> saveAndReturnFaculty(Mono<FacultyRequestDTO> facultyRequestDTO) {

        return facultyRequestDTO
                // convert to user entity
                .map(
                    requestDTO -> {
                        User userEntity = new User();
                        userEntity.setUserId(UUID.randomUUID());
                        userEntity.setUsername(requestDTO.getUsername());
                        userEntity.setPassword(requestDTO.getPassword());
                        userEntity.setRole("FACULTY");
                        userEntity.setStatus("PENDING");
                        return Pair.of(requestDTO, userEntity);
                    }
                )
//                .doOnNext(dtoEntityPair -> log.info("Created user entity {}", dtoEntityPair.getSecond().getUserId()))
                // save user entity
                .flatMap(
                        requestDTOUserEntityPair ->
                            userService.saveAndReturnUser(requestDTOUserEntityPair.getSecond())
                                    .map(savedUserEntity -> Pair.of(requestDTOUserEntityPair.getFirst(), savedUserEntity))
                )
                .doOnNext(dtoEntityPair -> log.info("Saved user entity {}", dtoEntityPair.getSecond().getUserId()))
                // convert to faculty entity
                .map(
                        requestDTOUserEntityPair -> {
                            Faculty facultyEntity = new Faculty();
                            facultyEntity.setFacultyId(UUID.randomUUID());
                            facultyEntity.setName(requestDTOUserEntityPair.getFirst().getName());
                            facultyEntity.setDepartment(requestDTOUserEntityPair.getFirst().getDepartment());
                            facultyEntity.setDesignation(requestDTOUserEntityPair.getFirst().getDesignation());
                            facultyEntity.setEmail(requestDTOUserEntityPair.getFirst().getEmail());
                            facultyEntity.setUserId(requestDTOUserEntityPair.getSecond().getUserId());
                            return Pair.of(facultyEntity, requestDTOUserEntityPair.getSecond());
                        }
                )
                // save faculty entity
                .flatMap(
                        facultyUserPair ->
                                facultyRepository.save(facultyUserPair.getFirst())
                                        .map(savedFacultyEntity -> Pair.of(savedFacultyEntity, facultyUserPair.getSecond()))
                )
                .map(
                        savedFacultyUserPair ->
                                new FacultyResponseDTO()
                                        .id(savedFacultyUserPair.getFirst().getFacultyId())
                                        .name(savedFacultyUserPair.getFirst().getName())
                                        .department(savedFacultyUserPair.getFirst().getDepartment())
                                        .designation(savedFacultyUserPair.getFirst().getDesignation())
                                        .email(savedFacultyUserPair.getFirst().getEmail())
                                        .username(savedFacultyUserPair.getSecond().getUsername())
                )
                .doOnSuccess(savedEntity ->
                        log.atInfo().log("Saved faculty record successfully")
                );
    }


    public Mono<Void> updateFaculty(Mono<FacultyRequestDTO> facultyRequestDTOMono, UUID facultyId) {
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