package com.proctor.service.enrollment.service;

import com.proctor.service.dto.EnrollmentResponseDTO;
import com.proctor.service.enrollment.repository.EnrollmentRepository;
import com.proctor.service.user.student.service.StudentService;
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
public class CourseEnrollmentService {


    private final EnrollmentRepository enrollmentRepository;
    private final StudentService studentService;

    public Mono<Void> deleteCourseEnrollmentById(Long courseEnrollmentId){
        return enrollmentRepository.deleteById(courseEnrollmentId)
                .doOnSuccess(voidType->log.atInfo().log("Deleted CourseEnrollment Successfully"));
    }

    public Flux<EnrollmentResponseDTO> fetchEnrollmentListOrById(Integer pageNumber,Integer pageSize,Long courseId){
        if(courseId !=null){
            return Flux.defer(()->enrollmentRepository.findCourseEnrollmentByCourseId(String.valueOf(courseId)))
                    .skip((pageNumber - 1L) * pageSize)
                    .take(pageSize)
                    .doOnComplete(()->log.atInfo().log("fetched enrollment details by courseID {}",courseId))
                    .map(enrollment ->{
                        EnrollmentResponseDTO enrollmentResponseDTO = new EnrollmentResponseDTO();
                        enrollmentResponseDTO.id(enrollment.getCourseEnrollId());
                        studentService.fetchStudentListOrById(0,0, enrollment.getRollNo()).subscribe(dto->enrollmentResponseDTO.student(dto));
                        enrollmentResponseDTO.status(enrollment.getStatus());
                        //Todo course responseDTO
                        return enrollmentResponseDTO;


                    });
        }
        return Flux.defer(enrollmentRepository::findAll)
                .skip((pageNumber - 1L) * pageSize)
                .take(pageSize)
                .doOnComplete(()->log.atInfo().log("fetched enrollment details by courseID {}",courseId))
                .map(enrollment ->{
                    EnrollmentResponseDTO enrollmentResponseDTO = new EnrollmentResponseDTO();
                    enrollmentResponseDTO.id(enrollment.getCourseEnrollId());
                    studentService.fetchStudentListOrById(0,0, enrollment.getRollNo()).subscribe(dto->enrollmentResponseDTO.student(dto));
                    enrollmentResponseDTO.status(enrollment.getStatus());
                    //Todo course responseDTO
                    return enrollmentResponseDTO;
                });

    }


}
