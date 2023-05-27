package tqs.loadconnect.core_backend.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "partner_store")
public class PartnerStore extends User  {   // PartnerStore is a user from the store

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address; //remove if maintain list

    @OneToMany
    private List<PickupPoint> pickupPoints;

    @OneToMany(mappedBy = "partnerStore")
    private List<Order> orders;

}
