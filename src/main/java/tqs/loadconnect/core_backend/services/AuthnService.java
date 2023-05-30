package tqs.loadconnect.core_backend.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tqs.loadconnect.core_backend.models.User;
import tqs.loadconnect.core_backend.models.UserDTO;
import tqs.loadconnect.core_backend.models.UserLoginDTO;
import tqs.loadconnect.core_backend.models.UserRegistDTO;
import tqs.loadconnect.core_backend.repositories.UserRepository;

@Service
public class AuthnService {

    @Autowired
    private UserRepository userRepository;

    public boolean registerUser(UserRegistDTO register) {
        User user = userRepository.findByEmail(register.getEmail()).orElse(null);
        if (user == null) {
            User user_n = new User();
            user_n.setName(register.getName());
            user_n.setEmail(register.getEmail());
            user_n.setPhoneNumber(register.getPhone_num());
            user_n.setPassword(register.getPassword());

            userRepository.save(user_n);
            return true;
        } else {
            return false;
        }

    }

    public UserDTO loginUser(UserLoginDTO login) {
        User user = userRepository.findByEmail(login.getEmail()).orElse(null);
        System.out.println("USER: " + user);
        if (user == null) {
            return null;
        } else {
            if (user.comparePassword(login.getPassword())) {
                System.out.println("YAY!");
                return new UserDTO(user);
            } else {
                System.out.println("WRONG PASSWORD");
                return null;
            }
        }
    }
}
