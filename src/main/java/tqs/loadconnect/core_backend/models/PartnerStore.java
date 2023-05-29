package tqs.loadconnect.core_backend.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
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

    @Column(name = "created_at")
    private LocalDate created_at;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "partnerStore")
    @JsonIgnoreProperties("partnerStore")
    private List<PickupPoint> pickupPoints;

    @Override
    public String toString() {
        return "PartnerStore{" +
                "id=" + id +
                ", ps_name='" + ps_name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public void addPickupPoint(PickupPoint pickupPoint) {
        pickupPoints.add(pickupPoint);
    }
}
