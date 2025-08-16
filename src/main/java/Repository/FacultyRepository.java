package Repository;

import entity.Faculty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface FacultyRepository extends R2dbcRepository<Faculty, Long> {

public Page<Faculty> findAll(Pageable pageable) ;

}
