package tqs.loadconnect.core_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tqs.loadconnect.core_backend.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findById(long id);

}
