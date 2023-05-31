package tqs.loadconnect.core_backend.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tqs.loadconnect.core_backend.models.PartnerStore;
import tqs.loadconnect.core_backend.repositories.PartnerStoreRepository;
import tqs.loadconnect.core_backend.repositories.PickupPRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    // repositories here
    public static String getPickupPointbyId(int id) {
        return null;
    }

    public static List<PartnerStore> getAllPartnerStores() {
        return null;
    }

}
