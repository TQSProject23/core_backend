package tqs.loadconnect.core_backend.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tqs.loadconnect.core_backend.models.PartnerStore;
import tqs.loadconnect.core_backend.models.PickupPoint;
import tqs.loadconnect.core_backend.repositories.PickupPRepository;

import java.util.List;

@Service
public class PickupPointService {

    @Autowired
    private PickupPRepository pickupPRepository;

    @Autowired
    private PartnerStoreService partnerStoreService;

    // add a new pickup point
    public PickupPoint addPickupPoint(PickupPoint pp) {
        System.out.println(pp);
        System.out.println("PS:" + pp.getPartnerStore());
        // verify if pp already exists
        PickupPoint existingStore = pickupPRepository.findById((long) pp.getId()).orElse(null);
        if (existingStore != null) {
            return null;
        }

        PartnerStore partnerStore = partnerStoreService.getPartnerStoreById(pp.getPartnerStore());
        partnerStore.addPickupPoint(pp);
        return pickupPRepository.save(pp);
    }

    // get all pickup points
    public List<PickupPoint> getAllPickupPoints() {
        return pickupPRepository.findAll();
    }

}
