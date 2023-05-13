package tqs.loadconnect.core_backend.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tqs.loadconnect.core_backend.Enums.OrderStatusEnum;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date dateOrdered;
    private OrderStatusEnum status; // PENDING, IN_TRANSIT, DELIVERED, CANCELLED
    private String description;
    private float price;
    private float weight;
    @ElementCollection
    private Map<Product, Integer> products = new HashMap<>();

    @OneToOne   // One order is associated with one User (from the Store)
    private Client client;  // Client is a user from the store

    @OneToOne   // One package is associated with one pickup point
    private User pickupPoint;   // Pickup point is a user



}
