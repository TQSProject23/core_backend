package tqs.loadconnect.core_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tqs.loadconnect.core_backend.Utils.Enums.OrderStatusEnum;
import tqs.loadconnect.core_backend.models.Order;
import tqs.loadconnect.core_backend.models.PickupPoint;
import tqs.loadconnect.core_backend.repositories.OrderRepository;
import tqs.loadconnect.core_backend.repositories.PickupPRepository;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PickupPRepository pickupPRepository;

    // get all orders
    public List<Order> getAllDeliveries() {
        return orderRepository.findAll();
    }

    // get order by ID
    public Order getDeliveryById(Integer orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    // get orders by pickup point
    public List<Order> getDeliveriesByPickupPointId(Integer pickupPointId) {
        return orderRepository.findByPickupPointId(pickupPointId);
    }

    public Order createOrder(Order order) {
        System.out.println(order.toString());
        System.out.println("PP:" + order.getPickupPoint());
        // verify if order already exists
        Order orderx = orderRepository.findById(order.getPickupPoint().getId()).orElse(null);
        if (orderx != null) {
            return null;
        }

        PickupPoint pp = pickupPRepository.findById((long) order.getPickupPoint().getId()).orElse(null);
        assert pp != null;
        pp.addOrder(order);
        System.out.println("PP final:" + pp);
        return orderRepository.save(order);
    }

    public Order updateOrder(Integer orderId, String orderStatus) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order == null) {
            return null;
        }
        // convert string to enum
        OrderStatusEnum orderStatusEnum = OrderStatusEnum.valueOf(orderStatus);
        order.setStatus(orderStatusEnum);
        return orderRepository.save(order);
    }

    public Integer getTotalOrders() {
        return orderRepository.findAll().size();
    }

    public Integer getTotalOnGoingOrders() {
        List<Order> orders = orderRepository.findAll();
        int count = 0;
        for (Order order : orders) {
            OrderStatusEnum status = order.getStatus();
            if (status == OrderStatusEnum.IN_TRANSIT || status == OrderStatusEnum.PENDING) {
                count++;
            }
        }
        return count;
    }

    public Integer getTotalOrdersFromLastMonth() {
        return orderRepository.findAll().size();
    }
}
