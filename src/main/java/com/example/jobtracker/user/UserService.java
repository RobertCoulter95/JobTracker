package com.example.jobtracker.user;

import com.example.jobtracker.auth.AuthenticationService;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {

    UserRepository userRepository;
    AuthenticationService authenticationService;

    public UserService(UserRepository userRepository, AuthenticationService authenticationService){
        this.userRepository = userRepository;
        this.authenticationService = authenticationService;
    }

    public void createUser(User user) throws Exception {
        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser.isPresent()) {
            throw new IllegalStateException("Username taken.");
        }
        user.setCreated(LocalDateTime.now());
        userRepository.save(user);
    }

    public void changePassword(String token, String currentPassword,String username,String newPassword){
        authenticationService.changePassword(token,currentPassword,username,newPassword);
    }

    public void recoverAccount(String email) {
        authenticationService.recoverAccount(email);
    }
}
