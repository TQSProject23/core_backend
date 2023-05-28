package tqs.loadconnect.core_backend.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "partner_store")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class PartnerStore {   // PartnerStore is a user from the store

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "ps_name")
    private String ps_name;

    @Column(name = "address")
    private String address; //remove if maintain list

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "partnerStore")
    private List<PickupPoint> pickupPoints;

    @Override
    public String toString() {
        return "PartnerStore{" +
                "id=" + id +
                ", ps_name='" + ps_name + '\'' +
                ", address='" + address + '\'' +
                ", pickupPoints=" + pickupPoints +
                '}';
    }

    public void addPickupPoint(PickupPoint pickupPoint) {
        pickupPoints.add(pickupPoint);
    }
}
