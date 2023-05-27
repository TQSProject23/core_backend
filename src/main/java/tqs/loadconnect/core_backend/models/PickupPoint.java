package tqs.loadconnect.core_backend.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;


@Entity
@Getter @Setter
@Table(name = "pickup_points")
public class PickupPoint {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    // one pickup point is associated with one PartnerStore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "partner_store_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private PartnerStore partnerStore;

    // one pickup point is associated with many orders
    @OneToMany(mappedBy = "pickupPoint")

    private List<Order> orders;

}
