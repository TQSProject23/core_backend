package tqs.loadconnect.core_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tqs.loadconnect.core_backend.Utils.Enums.OrderStatusEnum;
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
        System.out.println("createOrder: " + order.toString());
        Order newOrder = orderService.createOrder(order);
        if (newOrder == null) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok().body(newOrder);
        }
    }

    // return total number of orders
    @GetMapping("/total")
    public ResponseEntity<Integer> getTotalOrders() {
        Integer totalOrders = orderService.getTotalOrders();
        return ResponseEntity.ok().body(totalOrders);
    }

    // return total number of orders that are either PENDING or IN_TRANSIT
    @GetMapping("/total/on_going")
    public ResponseEntity<Integer> getTotalOnGoingOrders() {
        Integer totalOnGoingOrders = orderService.getTotalOnGoingOrders();
        return ResponseEntity.ok().body(totalOnGoingOrders);
    }

    // return number of orders from last month
    @GetMapping("/total/lastmonth")
    public ResponseEntity<Integer> getTotalOrdersFromLastMonth() {
        Integer totalOrdersFromLastMonth = orderService.getTotalOrdersFromLastMonth();
        return ResponseEntity.ok().body(totalOrdersFromLastMonth);
    }

    // get all orders
    @GetMapping("/all")
    public ResponseEntity<List<Order>> getAllDeliveries() {
        List<Order> deliveries = orderService.getAllDeliveries();
        return ResponseEntity.ok().body(deliveries);
    }

    // get order by ID
    @GetMapping("/{id}")
    public ResponseEntity<Order> getDeliveryById(@PathVariable(value="id") Integer orderId) {
        Order order = orderService.getDeliveryById(orderId);
        if (order == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(order);
        }
    }

    // get all orders by client email
    @GetMapping("/user/{client_email}")
    public ResponseEntity<List<Order>> getDeliveriesByClientEmail(@PathVariable(value="client_email") String clientEmail) {
        List<Order> orders = orderService.getDeliveriesByClientEmail(clientEmail);
        if (orders == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(orders);
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

    // get all orders by partner store
    @GetMapping("/partnerstore/{partner_store_id}")
    public ResponseEntity<List<Order>> getDeliveriesByPartnerStoreId(@PathVariable(value="partner_store_id") Integer partnerStoreId) {
            List<Order> orders = orderService.getDeliveriesByPartnerStoreId(partnerStoreId);
        if (orders == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(orders);
        }
    }

    // get number of orders by partner store email
    @GetMapping("/partnerstore/{partner_store_id}/total")
    public ResponseEntity<Integer> getTotalDeliveriesByPartnerStoreId(@PathVariable(value="partner_store_id") Integer partnerStoreId) {
        Integer totalOrders = orderService.getTotalDeliveriesByPartnerStoreId(partnerStoreId);
        if (totalOrders == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(totalOrders);
        }
    }

    // get number of orders by partner store that are either PENDING or IN_TRANSIT
    @GetMapping("/partnerstore/{partner_store_id}/total/on_going")
    public ResponseEntity<Integer> getTotalOnGoingDeliveriesByPartnerStoreId(@PathVariable(value="partner_store_id") Integer partnerStoreId) {
        Integer totalOnGoingOrders = orderService.getTotalOnGoingDeliveriesByPartnerStoreId(partnerStoreId);
        if (totalOnGoingOrders == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(totalOnGoingOrders);
        }
    }


    // update order
    @PutMapping("/update/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable(value="id") Integer orderId, @RequestBody String orderStatus) {
        Order updatedOrder = orderService.updateOrder(orderId, orderStatus);
        if (updatedOrder == null) {
            return ResponseEntity.notFound().build();
        } else {

            return ResponseEntity.ok().body(updatedOrder);
        }
    }
}
