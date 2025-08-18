package services;

import Repository.FacultyRepository;
import entity.Faculty;
import entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
public class Facultyservice {

    private final FacultyRepository facultyRepository;


    public Facultyservice(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }


    public Mono<Faculty> getByFacultyId(Long faculty_id){

        return facultyRepository.findById(faculty_id);
    }


    public Page<Faculty> getAllFaculty(int page, int size){
        return facultyRepository.findAll(PageRequest.of(page,size));
    }

    public Mono<Faculty> updateFaculty(Faculty faculty){
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(Long faculty_id){
        facultyRepository.deleteById(faculty_id);
    }




}
