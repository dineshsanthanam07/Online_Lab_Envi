package com.proctor.service.course.service;

import com.proctor.service.course.entity.Course;
import com.proctor.service.course.repository.CourseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class Courseservice {

private final CourseRepository courseRepository;

    public Courseservice(CourseRepository courseRepository){
        this.courseRepository=courseRepository;
    }



    public Page<Course> getAllCourses(int page, int size){
        return courseRepository.findAll(PageRequest.of(page, size));
    }

    public Mono<Course> getCourse(String course_id){
        return courseRepository.findById(course_id);
    }

    public Mono<Course> saveCourse(Course course){
        return courseRepository.save(course);
    }


    public void deleteCourse(String course_id){
        courseRepository.deleteById(course_id);
    }

}
