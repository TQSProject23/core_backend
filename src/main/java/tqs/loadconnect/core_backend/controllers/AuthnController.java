package tqs.loadconnect.core_backend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tqs.loadconnect.core_backend.models.PartnerStore;
import tqs.loadconnect.core_backend.models.PartnerStoreDTO;
import tqs.loadconnect.core_backend.models.PartnerStoreLoginDTO;
import tqs.loadconnect.core_backend.models.PartnerStoreRegistDTO;
import tqs.loadconnect.core_backend.services.AuthnService;


@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/authn")
public class AuthnController {

    @Autowired
    private final AuthnService authnService;

    @PostMapping("/register")
    public ResponseEntity<Boolean> registerPartnerStore(@RequestBody PartnerStoreRegistDTO register) {

        boolean result = authnService.registerPartnerStore(register);
        if (result) {
            return ResponseEntity.ok().body(true);
        } else {
            return ResponseEntity.badRequest().body(false);
        }

    }

    @PostMapping("/login")
    public ResponseEntity<PartnerStoreDTO> loginPartnerStore(@RequestBody PartnerStoreLoginDTO login) {
        System.out.println("LOGIN: " + login.toString());
        PartnerStoreDTO result = authnService.loginPartnerStore(login);
        System.out.println("RESULT: " + result);
        if (result != null) {
            System.out.println("YAY!" + result);
            return ResponseEntity.ok().body(result);
        } else {
            return ResponseEntity.badRequest().body(null);
        }

    }



}
