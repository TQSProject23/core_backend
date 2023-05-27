package tqs.loadconnect.core_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tqs.loadconnect.core_backend.models.Order;
import tqs.loadconnect.core_backend.repositories.OrderRepository;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    // get all orders
    public List<Order> getAllDeliveries() {
        return repository.findAll();
    }

    // get order by ID
    public Order getDeliveryById(Long orderId) {
        return repository.findById(orderId).orElse(null);
    }

    // get orders by pickup point
    public List<Order> getDeliveriesByPickupPointId(Integer pickupPointId) {
        return repository.findByPickupPointId(pickupPointId);
    }

    // get orders by partner store
    // ??
}
