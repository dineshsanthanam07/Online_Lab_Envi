package com.proctor.service.user.student.service;

import com.proctor.service.dto.StudentRequestDTO;
import com.proctor.service.dto.StudentResponseDTO;
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
                                //.rollNo(studentRecord.getRollNo())
                                //.batch(studentRecord.getBatch())
                               // .branch(studentRecord.getBranch())
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
//                            studentEntity.setRollNo(dto.getRollNo());
//                            studentEntity.setBatch(dto.getBatch());
//                            studentEntity.setBranch(dto.getBranch());
                            studentEntity.setDepartment(dto.getDepartment());
//                            User userEntity= new User();
//                            userEntity.setPassword(Encryptors.stronger(userdto.getPassword(),"encrypt"));
                            // TODO set User Id and Password
//                            studentEntity.setUserId(userEntity.getId());
                                return studentEntity;
                        }
                ).flatMap(studentRepository::save)
                .map(

                        studentRecord -> new StudentResponseDTO()
                                .username(studentRecord.getName())
                                .department(studentRecord.getDepartment())
                               // .rollNo(studentRecord.getRollNo())
                                //.branch(studentRecord.getBranch())
                                //.batch(studentRecord.getBatch())


                ).doOnSuccess(savedEntity -> log.atInfo().log("Saved Student record successfully"));
    }
}
