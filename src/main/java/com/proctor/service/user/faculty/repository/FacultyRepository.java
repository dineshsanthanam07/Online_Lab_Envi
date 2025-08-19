package com.proctor.service.user.faculty.repository;

import com.proctor.service.user.faculty.entity.Faculty;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyRepository extends R2dbcRepository<Faculty, Long> {}