package Repository;

import entity.User;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface UserRepository extends R2dbcRepository<User, String> {
    // Additional query methods can be defined here if needed
}
