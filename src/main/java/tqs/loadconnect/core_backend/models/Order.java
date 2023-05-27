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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public Date getDateOrdered() {
        return dateOrdered;
    }

    public void setDateOrdered(Date dateOrdered) {
        this.dateOrdered = dateOrdered;
    }

    public Date getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    public void setExpectedDeliveryDate(Date expectedDeliveryDate) {
        this.expectedDeliveryDate = expectedDeliveryDate;
    }

    public Date getPickup_date() {
        return pickup_date;
    }

    public void setPickup_date(Date pickup_date) {
        this.pickup_date = pickup_date;
    }

    public OrderStatusEnum getStatus() {
        return status;
    }

    public void setStatus(OrderStatusEnum status) {
        this.status = status;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public PickupPoint getPickupPoint() {
        return pickupPoint;
    }

    public void setPickupPoint(PickupPoint pickupPoint) {
        this.pickupPoint = pickupPoint;
    }

    public PartnerStore getPartnerStore() {
        return partnerStore;
    }

    public void setPartnerStore(PartnerStore partnerStore) {
        this.partnerStore = partnerStore;
    }


}
