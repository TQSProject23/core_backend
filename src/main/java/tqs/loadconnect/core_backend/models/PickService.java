package tqs.loadconnect.core_backend.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Data
@Entity
public class PickService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address; //remove if maintain list

    @OneToMany
    private List<PickupPoint> pickupPoints;
}
