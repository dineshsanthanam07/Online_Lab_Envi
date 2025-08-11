package services;

import Repository.FacultyRepository;
import entity.Faculty;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Facultyservice {

    private final FacultyRepository facultyRepository;


    public Facultyservice(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }





}
