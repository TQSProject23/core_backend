package tqs.loadconnect.core_backend.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartnerStoreDTO {

    private int id;
    private String ps_name;
    private String email;
    private String address;


    public PartnerStoreDTO(PartnerStore ps) {
        this.id = ps.getId();
        this.ps_name = ps.getPs_name();
        this.email = ps.getEmail();
        this.address = ps.getAddress();
    }

}
