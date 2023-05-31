package tqs.loadconnect.core_backend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tqs.loadconnect.core_backend.models.PartnerStore;
import tqs.loadconnect.core_backend.models.User;
import tqs.loadconnect.core_backend.models.UserDTO;
import tqs.loadconnect.core_backend.services.AdminService;
import java.util.List;


@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AdminController {

    @Autowired
    private final AdminService adminService;

    @GetMapping("/partnerstores")  // get all partner stores
    public List<PartnerStore> getPartnerStores() {
        return adminService.getAllPartnerStores();
    }


    @GetMapping("/pickuppoints/{id}")  // get a pickup point by id
    public String getPickupPoint(@PathVariable int id) {
        return adminService.getPickupPointbyId(id);
    }

    // return all users
    @GetMapping("/users")
    public List<UserDTO> getUsers() {
        return adminService.getAllUsers();
    }


}
