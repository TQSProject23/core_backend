package tqs.loadconnect.core_backend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tqs.loadconnect.core_backend.models.UserPP;
import tqs.loadconnect.core_backend.services.AdminService;
import java.util.List;


@RestController
@RequestMapping("api/v1/admin")
@CrossOrigin
@RequiredArgsConstructor
public class AdminController {

    // admin service
    private final AdminService adminService;

    @GetMapping("/test")    // only works after login!!
    public String test() {
        return "Admin Controller is working!";
    }

    @GetMapping("/pickuppoints")  // get all pickup points
    public List<UserPP> getPickupPoints() {
        return AdminService.getAllPickupPoints();
    }

    @GetMapping("/pickuppoints/{id}")  // get a pickup point by id
    public String getPickupPoint(@PathVariable int id) {
        return AdminService.getPickupPointbyId(id);
    }


}
