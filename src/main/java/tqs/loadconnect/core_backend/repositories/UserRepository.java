package tqs.loadconnect.core_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tqs.loadconnect.core_backend.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findById(long id);

}