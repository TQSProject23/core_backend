package tqs.loadconnect.core_backend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tqs.loadconnect.core_backend.models.PartnerStore;
import tqs.loadconnect.core_backend.services.AdminService;
import java.util.List;


@RestController
@RequestMapping("/admin")
@CrossOrigin
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/partnerstores")  // get all partner stores
    public List<PartnerStore> getPartnerStores() {
        return AdminService.getAllPartnerStores();
    }


    @GetMapping("/pickuppoints/{id}")  // get a pickup point by id
    public String getPickupPoint(@PathVariable int id) {
        return AdminService.getPickupPointbyId(id);
    }


}
