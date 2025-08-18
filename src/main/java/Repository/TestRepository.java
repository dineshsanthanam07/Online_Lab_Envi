package Repository;

import entity.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface TestRepository extends R2dbcRepository<Test,Long> {

    Page<Test> findAll(Pageable pageable);

    Page<Test> findAllByCourseId(Pageable pageable,String courseId);

}
