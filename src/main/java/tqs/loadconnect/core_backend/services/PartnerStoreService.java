package tqs.loadconnect.core_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tqs.loadconnect.core_backend.Utils.Enums.PickupPEnum;
import tqs.loadconnect.core_backend.models.PartnerStore;
import tqs.loadconnect.core_backend.models.PickupPoint;
import tqs.loadconnect.core_backend.repositories.PartnerStoreRepository;

import java.util.List;

@Service
public class PartnerStoreService {

    @Autowired
    private PartnerStoreRepository partnerStoreRepository;

    // add a new partner store
    public PartnerStore addStore(PartnerStore store) {
        // verify if store already exists
        PartnerStore existingStore = partnerStoreRepository.findById((long) store.getId()).orElse(null);
        if (existingStore != null) {
            return null;
        }
        return partnerStoreRepository.save(store);
    }

    // get all partner stores
    public List<PartnerStore> getAllStores() {
        return partnerStoreRepository.findAll();
    }

    // get partner store by ID
    public PartnerStore getStoreById(Long storeId) {
        return partnerStoreRepository.findById(storeId).orElse(null);
    }

    public PartnerStore getPartnerStoreById(int id) {
        return partnerStoreRepository.findById((long) id).orElse(null);
    }

    public List<PickupPoint> getAllPickupPointsByPartnerStoreId(Integer storeId) {
        PartnerStore store = partnerStoreRepository.findById((long) storeId).orElse(null);
        if (store == null) {
            return null;
        }
        return store.getPickupPoints();
    }

    public Integer getTotalPartnerStores() {
        return partnerStoreRepository.findAll().size();
    }

    public Integer getTotalPartnerStoresLastMonth() {
        return partnerStoreRepository.findAll().size();
    }

    public Integer getNumberOfPickupPointsByPartnerStoreId(Integer storeId) {
        PartnerStore store = partnerStoreRepository.findById((long) storeId).orElse(null);
        if (store == null) {
            return null;
        }
        List<PickupPoint> pickupPoints = store.getPickupPoints();
        pickupPoints.removeIf(pickupPoint -> pickupPoint.getPp_status() != PickupPEnum.ACCEPTED);
        return pickupPoints.size();
    }

    public PartnerStore getStoreByEmail(String email) {
        return partnerStoreRepository.findByEmail(email);
    }

    public Integer getNumberOfPendingPickupPointsByPartnerStoreId(Integer storeId) {
        PartnerStore store = partnerStoreRepository.findById((long) storeId).orElse(null);
        if (store == null) {
            return null;
        }
        List<PickupPoint> pickupPoints = store.getPickupPoints();
        pickupPoints.removeIf(pickupPoint -> pickupPoint.getPp_status() != PickupPEnum.PENDING);
        return pickupPoints.size();
    }
}
