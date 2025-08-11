package Repository;

import entity.Faculty;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyRepository extends R2dbcRepository<Faculty, Long> {



}
