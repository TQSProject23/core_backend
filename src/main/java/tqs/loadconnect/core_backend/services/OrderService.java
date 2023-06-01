package tqs.loadconnect.core_backend.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tqs.loadconnect.core_backend.Utils.Enums.OrderStatusEnum;
import tqs.loadconnect.core_backend.models.Order;
import tqs.loadconnect.core_backend.models.PickupPoint;
import tqs.loadconnect.core_backend.repositories.OrderRepository;
import tqs.loadconnect.core_backend.repositories.PickupPRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final PickupPRepository pickupPRepository;


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

    public Order addOrder(Order order) {
        order.getPickupPoint().addOrder(order);
        return orderRepository.save(order);
    }

    public Order createOrder(Order order) {

        addOrder(order);
        // sleep for 15 seconds
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // update order status TO IN_TRANSIT
        updateOrder(order.getId(), "IN_TRANSIT");
        return order;
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

    public List<Order> getDeliveriesByPartnerStoreId(Integer partnerStoreId) {
        List<Order> allOrders = orderRepository.findAll();
        List<Order> final_lst = new ArrayList<>();
        // allOrders.removeIf(order -> order.getPickupPoint().getPartnerStore().getId() != partnerStoreId);
        for (Order order : allOrders) {
            if (order.getPickupPoint().getPartnerStore().getId() == partnerStoreId) {
                final_lst.add(order);
            }
        }
        return final_lst;
    }

    public Integer getTotalDeliveriesByPartnerStoreId(Integer partnerStoreId) {
        List<Order> allOrders = orderRepository.findAll();
        List<Order> final_lst = new ArrayList<>();
        for (Order order : allOrders) {
            if (order.getPickupPoint().getPartnerStore().getId() != partnerStoreId) {
                final_lst.add(order);
            }
        }
        return final_lst.size();
    }

    public Integer getTotalOnGoingDeliveriesByPartnerStoreId(Integer partnerStoreId) {
        List<Order> allOrders = orderRepository.findAll();

        int count = 0;
        for (Order order : allOrders) {
            OrderStatusEnum status = order.getStatus();
            if (order.getPickupPoint().getPartnerStore().getId() == partnerStoreId) {
                if (status == OrderStatusEnum.IN_TRANSIT || status == OrderStatusEnum.PENDING) {
                    count++;
                }
            }
        }
        return count;
    }

    public List<Order> getDeliveriesByClientEmail(String clientEmail) {
        List<Order> allOrders = orderRepository.findAll();
        List<Order> final_lst = new ArrayList<>();
        for (Order order : allOrders) {
            if (order.getClientEmail().equals(clientEmail)) {
                final_lst.add(order);
            }
        }
        return final_lst;
    }
}
