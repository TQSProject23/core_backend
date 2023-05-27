package tqs.loadconnect.core_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tqs.loadconnect.core_backend.models.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByPickupPointId(Integer pickupPointId);

}
