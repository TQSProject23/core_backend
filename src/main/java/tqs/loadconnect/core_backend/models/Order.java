package tqs.loadconnect.core_backend.models;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import tqs.loadconnect.core_backend.Utils.Enums.OrderStatusEnum;

import java.time.LocalDate;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private float price;

    @Column(name = "weight")
    private float weight;

    @Column(name = "date_ordered")
    private LocalDate dateOrdered;

    @Column(name = "expected_delivery_date")
    private LocalDate expectedDeliveryDate;

    @Column(name = "pickup_date")
    private LocalDate pickup_date;

    @Column(name = "status")
    private OrderStatusEnum status; // PENDING, IN_TRANSIT, DELIVERED, CANCELLED


    // associated with a Client
    @Column(name = "client_name")
    private String clientName;

    @Column(name = "client_email")
    private String clientEmail;


    @ManyToOne
    @JoinColumn(name = "pickupPoint", nullable = false)
    @JsonIgnoreProperties({"orders"})
    private PickupPoint pickupPoint;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", weight=" + weight +
                ", dateOrdered=" + dateOrdered +
                ", expectedDeliveryDate=" + expectedDeliveryDate +
                ", pickup_date=" + pickup_date +
                ", status=" + status +
                ", clientName='" + clientName + '\'' +
                ", clientEmail='" + clientEmail + '\'' +
                '}';
    }
}
