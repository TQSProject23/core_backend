package tqs.loadconnect.core_backend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserRegistDTO {

    private String name;
    private String email;
    private String password;
    private String phone_num;

}
