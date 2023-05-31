package tqs.loadconnect.core_backend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartnerStoreLoginDTO {
    private String email;
    private String ps_password;

}
