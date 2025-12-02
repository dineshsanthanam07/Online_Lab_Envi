package com.proctor.service.user.student.service;

import com.proctor.service.dto.StudentRequestDTO;
import com.proctor.service.dto.StudentResponseDTO;
import com.proctor.service.user.UserService;
import com.proctor.service.user.entity.User;
import com.proctor.service.user.student.entity.Student;
import com.proctor.service.user.student.repository.StudentRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class StudentService {

    private final StudentRepository studentRepository;
    private final UserService userService;

    public Mono<Void> deleteStudentById(Long rollNo){
        return studentRepository.deleteById(rollNo)
                .doOnSuccess(voidType -> log.atInfo().log("Deleted Student Record successfully"));
    }
    public Flux<StudentResponseDTO> fetchStudentListOrById(Integer pageNumber,Integer pageSize, Long rollNo){
        return Mono.justOrEmpty(rollNo)
                .flatMap(studentRepository::findById)
                .flux()
                .switchIfEmpty(
                        subscriber -> studentRepository.findAll(Sort.by(Sort.Order.asc("rollNo")))
                                .skip((pageNumber -1L)* pageSize)
                                .take(pageSize)
                                .doOnComplete(()->log.atInfo().log("Fetched Student List"))
                ).map(
                        studentRecord -> new StudentResponseDTO()
                                .rollNo(String.valueOf(studentRecord.getRollNo()))
                                .batch(studentRecord.getBatch())
                                .branch(studentRecord.getBranch())
                                .department(studentRecord.getDepartment())
                                .username(studentRecord.getName())
                );
    }

    public Mono<StudentResponseDTO> saveAndReturnStudent(Mono<StudentRequestDTO> studentRequestDTO){
        return studentRequestDTO
                .map(
                        dto -> {
                            Student studentEntity = new Student();
                            studentEntity.setName(dto.getName());
                            studentEntity.setRollNo(Long.valueOf(dto.getRollNo()));
                            studentEntity.setBatch(dto.getBatch());
                            studentEntity.setBranch(dto.getBranch());
                            studentEntity.setDepartment(dto.getDepartment());
                            User user = new User();
                            user.setPassword(dto.getPassword());
                            user.setUsername(String.valueOf(dto.getRollNo()));
                            user.setRole("STUDENT");
                            user.setStatus("PENDING");
                            userService.saveAndReturnUser(user).map(
                                    userres->{studentEntity.setUserId(userres.getUserId());
                                        return user;
                                    }
                            );
                                return studentEntity;
                        }
                ).flatMap(studentRepository::save)
                .map(

                        studentRecord -> new StudentResponseDTO()
//                                .id(studentRecord.getUserId())
                                .username(String.valueOf(studentRecord.getRollNo()))
                                .name(studentRecord.getName())
                                .department(studentRecord.getDepartment())
                                .rollNo(String.valueOf(studentRecord.getRollNo()))
                                .branch(studentRecord.getBranch())
                                .batch(studentRecord.getBatch())



                ).doOnSuccess(savedEntity -> log.atInfo().log("Saved Student record successfully"));
    }
    public Mono<Void> updateStudent(Mono<StudentRequestDTO> studentRequestDTOMono, Long rollNo) {
        return studentRequestDTOMono.doOnNext(
                studentRequestDTO -> {
                    studentRepository.findById(rollNo)
                            .map(
                                    fetchedEntity -> {
                                        fetchedEntity.setName(studentRequestDTO.getName());
                                        fetchedEntity.setDepartment(studentRequestDTO.getDepartment());
                                        fetchedEntity.setBranch(studentRequestDTO.getBranch());
                                        fetchedEntity.setBatch(studentRequestDTO.getBatch());
                                        fetchedEntity.setName(studentRequestDTO.getName());
                                        return fetchedEntity;
                                    }
                            ).doOnNext(studentRepository::save);
                }
        ).then();
    }
}
