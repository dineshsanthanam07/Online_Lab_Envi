package com.proctor.service.course.service;

import com.proctor.service.course.entity.Course;
import com.proctor.service.course.repository.CourseRepository;
import com.proctor.service.dto.CourseRequestDTO;
import com.proctor.service.dto.CourseResponseDTO;
import com.proctor.service.dto.FacultyResponseDTO;
import com.proctor.service.user.faculty.service.FacultyService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CourseApiService {

    private final CourseRepository courseRepository;
    private final FacultyService facultyService;

    public Mono<Void> deleteCourseById(UUID courseId) {
        return courseRepository.deleteById(courseId)
                .doOnSuccess(voidType -> log.info("Deleted course successfully"));
    }

    public Flux<CourseResponseDTO> fetchCourseListBasedOnSelection(UUID courseId, Integer pageNumber, Integer pageSize) {
        return Mono.justOrEmpty(courseId)
                .flatMap(courseRepository::findById)
                .flux()
                .switchIfEmpty(
                        courseRepository.findAll(Sort.by(Sort.Direction.ASC, "courseId"))
                                .skip((pageSize - 1) * pageNumber)
                                .take(pageSize)
                )
                .flatMap(
                        course -> facultyService.fetchFacultyListOrById(null, null, course.getFacultyId()).map(facultyEntity -> Pair.of(course, facultyEntity))
                )
                .map(
                        coursePair -> new CourseResponseDTO()
                                .id(coursePair.getFirst().getCourseId())
                                .name(coursePair.getFirst().getCourseName())
                                .createdAt(coursePair.getFirst().getCreatedAt())
                                .faculty(coursePair.getSecond())
                ).doOnComplete(() -> log.info("Fetched requested course list"));
    }

    public Mono<CourseResponseDTO> saveOrUpdateCourse(Mono<CourseRequestDTO> courseRequestDTO, UUID courseId) {
        return Mono.justOrEmpty(courseId)
                .flatMap(courseRepository::findById)
                .flatMap(
                        courseEntityToUpdate ->
                                courseRequestDTO.map(
                                        requestDTO -> {
                                            courseEntityToUpdate.setCourseName(requestDTO.getName());
                                            courseEntityToUpdate.setFacultyId(requestDTO.getFacultyId());
                                            return courseEntityToUpdate;
                                        }
                                )
                )
                .switchIfEmpty(
                        courseRequestDTO
                                .map(
                                        requestDTO -> {
                                            Course courseEntity = new Course();
                                            courseEntity.setCourseId(UUID.randomUUID());
                                            courseEntity.setCourseName(requestDTO.getName());
                                            courseEntity.setFacultyId(requestDTO.getFacultyId());
                                            courseEntity.setCreatedAt(OffsetDateTime.now(ZoneOffset.UTC));
                                            return courseEntity;
                                        }
                                )
                ).doOnNext(courseRepository::save)
                .flatMap(
                        courseEntity ->
                                facultyService.
                                        fetchFacultyListOrById(null, null, courseEntity.getFacultyId())
                                        .singleOrEmpty()
                                        .map(
                                                facultyEntity ->
                                                        new FacultyResponseDTO()
                                                                .id(facultyEntity.getId())
                                                                .name(facultyEntity.getName())
                                                                .department(facultyEntity.getDepartment())
                                                                .username(facultyEntity.getUsername())
                                                                .designation(facultyEntity.getDesignation())
                                                                .email(facultyEntity.getEmail())
                                        )
                                        .map(facultyResponseDTO -> Pair.of(courseEntity, facultyResponseDTO))
                ).map(
                        courseAndFacultyEntityPair -> new CourseResponseDTO()
                                .id(courseAndFacultyEntityPair.getFirst().getCourseId())
                                .name(courseAndFacultyEntityPair.getFirst().getCourseName())
                                .createdAt(courseAndFacultyEntityPair.getFirst().getCreatedAt())
                                .faculty(courseAndFacultyEntityPair.getSecond())
                );
    }
}