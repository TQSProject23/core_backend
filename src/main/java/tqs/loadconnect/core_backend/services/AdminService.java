package tqs.loadconnect.core_backend.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tqs.loadconnect.core_backend.models.PartnerStore;
import tqs.loadconnect.core_backend.repositories.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;

    public static String getPickupPointbyId(int id) {
        return null;
    }

    public static List<PartnerStore> getAllPartnerStores() {
        return null;
    }
}
