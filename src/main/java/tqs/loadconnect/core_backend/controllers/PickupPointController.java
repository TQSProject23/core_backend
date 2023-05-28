package tqs.loadconnect.core_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tqs.loadconnect.core_backend.models.PartnerStore;
import tqs.loadconnect.core_backend.models.PickupPoint;
import tqs.loadconnect.core_backend.services.PickupPointService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pps")
@CrossOrigin
public class PickupPointController {

    @Autowired private PickupPointService pickupPointService;

    // add a new pickup point
    @PostMapping("/add")
    public ResponseEntity<PickupPoint> addPickupPoint(@RequestBody PickupPoint pickupPoint) {
        System.out.println("pre: " + pickupPoint);
        PickupPoint newPickupPoint = pickupPointService.addPickupPoint(pickupPoint);
        if (newPickupPoint == null) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok().body(newPickupPoint);
        }
    }

    // update a pickup point status
    @PutMapping("/update/{id}")
    public ResponseEntity<PickupPoint> updatePickupPointStatus(@PathVariable(value="id") int pickupPointId, @RequestBody String ppStatus) {
        PickupPoint updatedPickupPoint = pickupPointService.updatePickupPointStatus(pickupPointId, ppStatus);
        if (updatedPickupPoint == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(updatedPickupPoint);
        }
    }

    // get all pickup points
    @GetMapping("/all")
    public ResponseEntity<List<PickupPoint>> getAllPickupPoints() {
        List<PickupPoint> pickupPoints = pickupPointService.getAllPickupPoints();
        if (pickupPoints == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(pickupPoints);
        }
    }


    // get all pickup points by city
    @GetMapping("/city/{city}")
    public ResponseEntity<List<PickupPoint>> getAllPickupPointsByCity(@PathVariable(value="city") String city) {
        List<PickupPoint> pickupPoints = pickupPointService.getAllPickupPointsByCity(city);
        if (pickupPoints == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(pickupPoints);
        }
    }



}
