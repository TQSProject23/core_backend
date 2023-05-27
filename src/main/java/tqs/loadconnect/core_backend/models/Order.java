package tqs.loadconnect.core_backend.models;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import tqs.loadconnect.core_backend.Utils.Enums.OrderStatusEnum;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private float price;

    @Column(name = "weight")
    private float weight;

    @Column(name = "date_ordered")
    private Date dateOrdered;

    @Column(name = "expected_delivery_date")
    private Date expectedDeliveryDate;

    @Column(name = "pickup_date")
    private Date pickup_date;

    @Column(name = "status")
    private OrderStatusEnum status; // PENDING, IN_TRANSIT, DELIVERED, CANCELLED


    // associated with a Client
    @Column(name = "client_name")
    private String clientName;

    @Column(name = "client_email")
    private String clientEmail;


    // many orders are associated with one pickup point and one partner store
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pickup_point_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PickupPoint pickupPoint;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PartnerStore partnerStore;
}
