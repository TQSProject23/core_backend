package tqs.loadconnect.core_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tqs.loadconnect.core_backend.models.Order;
import tqs.loadconnect.core_backend.services.OrderService;
import tqs.loadconnect.core_backend.services.PickupPointService;

import java.util.List;


@RestController
@CrossOrigin(origins="*")
@RequestMapping("/api/v1/orders")
public class OrderController {


    @Autowired
    private OrderService orderService;


    private PickupPointService pickupPointService;

    // create a new order
    @PostMapping("/new")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order newOrder = orderService.createOrder(order);
        return ResponseEntity.ok().body(newOrder);
    }


    // get all orders
    @GetMapping("/all")
    public ResponseEntity<List<Order>> getAllDeliveries() {
        List<Order> deliveries = orderService.getAllDeliveries();
        return ResponseEntity.ok().body(deliveries);
    }

    // get order by ID
    @GetMapping("/{id}")
    public ResponseEntity<Order> getDeliveryById(@PathVariable(value="id") Long orderId) {
        Order order = orderService.getDeliveryById(orderId);
        if (order == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(order);
        }
    }

    // get orders by pickup point
    @GetMapping("/ppoint/{pickup_point_id}")
    public ResponseEntity<List<Order>> getDeliveriesByPickupPointId(@PathVariable(value="pickup_point_id") Integer pickupPointId) {
        List<Order> orders = orderService.getDeliveriesByPickupPointId(pickupPointId);
        if (orders == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(orders);
        }
    }

}
