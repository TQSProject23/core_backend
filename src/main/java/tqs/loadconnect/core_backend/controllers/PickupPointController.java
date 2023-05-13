package tqs.loadconnect.core_backend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tqs.loadconnect.core_backend.services.PickupPointService;

@RestController
@RequestMapping("/pps")
@CrossOrigin
@RequiredArgsConstructor
public class PickupPointController {

    // pickup point service
    private final PickupPointService pickupPointService;

    @GetMapping("/orders")  // get all orders from a CERTAIN pickup point
    public String getOrders(@RequestParam int pickupPointId ) {
        return pickupPointService.getAllOrders(pickupPointId);
    }


}
