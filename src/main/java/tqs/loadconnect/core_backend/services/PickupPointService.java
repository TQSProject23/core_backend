package tqs.loadconnect.core_backend.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tqs.loadconnect.core_backend.Utils.Enums.PickupPEnum;
import tqs.loadconnect.core_backend.models.Order;
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

        PartnerStore partnerStore = partnerStoreService.getPartnerStoreById(pp.getPartnerStore().getId());
        partnerStore.addPickupPoint(pp);
        System.out.println("PS final:" + partnerStore);
        return pickupPRepository.save(pp);
    }

    // get all pickup points
    public List<PickupPoint> getAllPickupPoints() {

        List<PickupPoint> pickupPoints = pickupPRepository.findAll();

        // keep only pickup points with status "ACCCEPTED"
        pickupPoints.removeIf(pickupPoint -> pickupPoint.getPp_status() != PickupPEnum.ACCEPTED);

        return pickupPoints;
    }

    public List<PickupPoint> getAllPickupPointsByCity(String city) {
        return pickupPRepository.findAllByCity(city);
    }

    public PickupPoint updatePickupPointStatus(int pickupPointId, String ppStatus) {
        PickupPoint pickupPoint = pickupPRepository.findById((long) pickupPointId).orElse(null);
        if (pickupPoint == null) {
            return null;
        }
        // convert string to enum
        PickupPEnum ppStatusEnum = PickupPEnum.valueOf(ppStatus);
        pickupPoint.setPp_status(ppStatusEnum);
        return pickupPRepository.save(pickupPoint);
    }

    public int countPickupPoints() {
        return pickupPRepository.findAll().size();
    }



    public PickupPoint getDeliveryById(Integer ppId) {
        return pickupPRepository.findById((long) ppId).orElse(null);
    }

    public List<Order> getAllOrdersByPickupPoint(int pickupPointId) {
        PickupPoint pickupPoint = pickupPRepository.findById((long) pickupPointId).orElse(null);
        if (pickupPoint == null) {
            return null;
        }
        return pickupPoint.getOrders();
    }
}
