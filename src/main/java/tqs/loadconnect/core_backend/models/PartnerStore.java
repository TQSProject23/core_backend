package tqs.loadconnect.core_backend.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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

    @Column(name = "email")
    private String email;

    @Column(name = "ps_name")
    private String ps_name;

    @Column(name = "ps_password")
    private String ps_password;

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

    public void setPassword(String password) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        this.ps_password = encoder.encode(password);
    }

    public boolean comparePassword(String password) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(password, this.ps_password);
    }
}
