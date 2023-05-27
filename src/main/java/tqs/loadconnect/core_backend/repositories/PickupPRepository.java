package tqs.loadconnect.core_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tqs.loadconnect.core_backend.models.PickupPoint;

@Repository
public interface PickupPRepository extends JpaRepository<PickupPoint, Long>
{

}
