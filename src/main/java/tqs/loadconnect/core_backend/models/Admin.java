package tqs.loadconnect.core_backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
public class Admin extends User {

    // idek if this is needed
    @NonNull    // lombok or springframework?
    private final RoleEnum role = RoleEnum.ADMIN;

}
