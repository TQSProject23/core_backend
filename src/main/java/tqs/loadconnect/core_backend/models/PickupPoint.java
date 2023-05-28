package tqs.loadconnect.core_backend.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class PickupPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "pp_name")
    private String pp_name;

    @Column(name = "address")
    private String address;

    // one pickup point is associated with one PartnerStore
    @ManyToOne
    @JsonIgnoreProperties({"pickupPoints", "orders"})
    @JoinColumn(name = "partnerStore", nullable = false)
    private PartnerStore partnerStore;

    // one pickup point is associated with many orders
    @OneToMany(cascade = {CascadeType.ALL, CascadeType.DETACH}, mappedBy = "pickupPoint")
    private List<Order> orders;


    @Override
    public String toString() {
        return "PickupPoint{" +
                "id=" + id +
                ", pp_name='" + pp_name + '\'' +
                ", address='" + address + '\'' +
                ", partnerStore=" + partnerStore +
                ", orders=" + orders +
                '}';
    }
}
