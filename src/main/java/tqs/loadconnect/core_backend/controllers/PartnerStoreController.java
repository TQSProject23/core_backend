package tqs.loadconnect.core_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tqs.loadconnect.core_backend.models.PartnerStore;
import tqs.loadconnect.core_backend.models.PickupPoint;
import tqs.loadconnect.core_backend.services.PartnerStoreService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/partnerstore")
@CrossOrigin(origins="*")
public class PartnerStoreController {

    @Autowired
    private PartnerStoreService partnerStoreService;

    // add a new partner store
    @PostMapping("/add")
    public ResponseEntity<PartnerStore> addStore(@RequestBody PartnerStore store) {
        System.out.println("addStore");
        System.out.println(store.toString());
        PartnerStore newStore = partnerStoreService.addStore(store);
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
    public ResponseEntity<PartnerStore> getPartnerStoreById(@PathVariable(value="id") Integer storeId) {

        PartnerStore store = partnerStoreService.getStoreById(Long.valueOf(storeId));
        if (store == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(store);
        }
    }

    // get partner store by email
    @GetMapping("/email/{email}")
    public ResponseEntity<PartnerStore> getPartnerStoreByEmail(@PathVariable(value="email") String email) {

        PartnerStore store = partnerStoreService.getStoreByEmail(email);
        if (store == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(store);
        }
    }

    // get all pickup points by partner store
    @GetMapping("/{id}/pps")
    public ResponseEntity<List<PickupPoint>> getAllPickupPointsByPartnerStoreId(@PathVariable(value="id") Integer storeId) {
        List<PickupPoint> pickupPoints = partnerStoreService.getAllPickupPointsByPartnerStoreId(storeId);
        if (pickupPoints == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(pickupPoints);
        }
    }

    // get number of pickup points by partner store ACCEPTED ONES
    @GetMapping("/{id}/pps/total")
    public ResponseEntity<Integer> getNumberOfPickupPointsByPartnerStoreId(@PathVariable(value="id") Integer storeId) {
        Integer numberOfPickupPoints = partnerStoreService.getNumberOfPickupPointsByPartnerStoreId(storeId);
        if (numberOfPickupPoints == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(numberOfPickupPoints);
        }
    }

    // get number of pickup points PENDING by partner store
    @GetMapping("/{id}/pps/pending")
    public ResponseEntity<Integer> getNumberOfPendingPickupPointsByPartnerStoreId(@PathVariable(value="id") Integer storeId) {
        Integer numberOfPendingPickupPoints = partnerStoreService.getNumberOfPendingPickupPointsByPartnerStoreId(storeId);
        if (numberOfPendingPickupPoints == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(numberOfPendingPickupPoints);
        }
    }


    // return total number of partner stores
    @GetMapping("/total")
    public ResponseEntity<Integer> getTotalPartnerStores() {
        Integer totalPartnerStores = partnerStoreService.getTotalPartnerStores();
        return ResponseEntity.ok().body(totalPartnerStores);
    }

    // return number of partner stores created in the last month
    @GetMapping("/total/lastmonth")
    public ResponseEntity<Integer> getTotalPartnerStoresLastMonth() {
        // TODO: implement this correctly
        Integer totalPartnerStoresLastMonth = partnerStoreService.getTotalPartnerStoresLastMonth();
        return ResponseEntity.ok().body(totalPartnerStoresLastMonth);
    }

}

