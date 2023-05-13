package tqs.loadconnect.core_backend.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tqs.loadconnect.core_backend.models.UserPP;
import tqs.loadconnect.core_backend.repositories.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;

    public static List<UserPP> getAllPickupPoints() {
        return null;
    }

    public static String getPickupPointbyId(int id) {
        return null;
    }
}
