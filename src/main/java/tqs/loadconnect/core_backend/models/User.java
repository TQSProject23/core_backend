package tqs.loadconnect.core_backend.models;

import jakarta.persistence.*;
import lombok.*;
import tqs.loadconnect.core_backend.Enums.RoleEnum;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String email;
    private String password;
    private String name;
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private RoleEnum role;


}
