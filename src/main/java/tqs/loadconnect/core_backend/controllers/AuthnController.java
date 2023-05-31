package tqs.loadconnect.core_backend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tqs.loadconnect.core_backend.models.User;
import tqs.loadconnect.core_backend.models.UserDTO;
import tqs.loadconnect.core_backend.models.UserLoginDTO;
import tqs.loadconnect.core_backend.models.UserRegistDTO;
import tqs.loadconnect.core_backend.services.AuthnService;


@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/authn")
public class AuthnController {

    @Autowired
    private final AuthnService authnService;

    @PostMapping("/register")
    public ResponseEntity<Boolean> registerUser(@RequestBody UserRegistDTO register) {

        boolean result = authnService.registerUser(register);
        if (result) {
            return ResponseEntity.ok().body(true);
        } else {
            return ResponseEntity.badRequest().body(false);
        }

    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> loginUser(@RequestBody UserLoginDTO login) {

        UserDTO result = authnService.loginUser(login);
        System.out.println("RESULT: " + result);
        if (result != null) {
            System.out.println("YAY!" + result);
            return ResponseEntity.ok().body(result);
        } else {
            return ResponseEntity.badRequest().body(null);
        }

    }



}
