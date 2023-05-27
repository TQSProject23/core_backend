package tqs.loadconnect.core_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tqs.loadconnect.core_backend.models.PartnerStore;
import tqs.loadconnect.core_backend.services.PartnerStoreService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/partnerstore")
@CrossOrigin
public class PartnerStoreController {

    @Autowired
    private PartnerStoreService partnerStoreService;

    // add a new partner store
    @PostMapping("/add")
    public ResponseEntity<PartnerStore> addStore(@RequestBody PartnerStore pickupPoint) {
        PartnerStore newStore = partnerStoreService.addStore(pickupPoint);
        return ResponseEntity.ok().body(newStore);
    }

    // get all partner stores
    @GetMapping("/all")
    public ResponseEntity<List<PartnerStore>> getAllPartnerStores() {
        List<PartnerStore> stores = partnerStoreService.getAllStores();
        if (stores == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(stores);
        }
    }

    // get partner store by ID
    @GetMapping("/{id}")
    public ResponseEntity<PartnerStore> getPartnerStoreById(@PathVariable(value="id") Long storeId) {
        PartnerStore store = partnerStoreService.getStoreById(storeId);
        if (store == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(store);
        }
    }

}

