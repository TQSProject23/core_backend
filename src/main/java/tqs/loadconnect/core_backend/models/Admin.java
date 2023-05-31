package tqs.loadconnect.core_backend.models;

import jakarta.persistence.*;
import lombok.NonNull;
import tqs.loadconnect.core_backend.Utils.Enums.RoleEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "admin")
@Getter
@Setter
@NoArgsConstructor
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "email")
    private String email;

    @Column(name = "admin_name")
    private String admin_name;

    @Column(name = "admin_pswd")
    private String admin_pswd;
    @NonNull    // lombok or springframework?
    private final RoleEnum role = RoleEnum.ADMIN;
}
