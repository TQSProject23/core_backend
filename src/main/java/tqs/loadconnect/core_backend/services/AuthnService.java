package tqs.loadconnect.core_backend.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tqs.loadconnect.core_backend.models.*;
import tqs.loadconnect.core_backend.repositories.PartnerStoreRepository;

@RequiredArgsConstructor
@Service
public class AuthnService {

    private final PartnerStoreRepository partnerStoreRepository;

    public boolean registerPartnerStore(PartnerStoreRegistDTO register) {
        System.out.println("REGISTER: " + register.toString());
        PartnerStore ps = partnerStoreRepository.findByEmail(register.getEmail());
        if (ps == null) {
            PartnerStore ps_n = new PartnerStore();
            ps_n.setPs_name(register.getPs_name());
            ps_n.setEmail(register.getEmail());
            ps_n.setAddress(register.getAddress());
            ps_n.setPassword(register.getPs_password());
            partnerStoreRepository.save(ps_n);
            return true;
        } else {
            return false;
        }

    }

    public PartnerStoreDTO loginPartnerStore(PartnerStoreLoginDTO login) {
        PartnerStore ps = partnerStoreRepository.findByEmail(login.getEmail());
        if (ps == null) {
            return null;
        } else {
            if (ps.comparePassword(login.getPs_password())) {
                return new PartnerStoreDTO(ps);
            } else {
                return null;
            }
        }
    }
}
