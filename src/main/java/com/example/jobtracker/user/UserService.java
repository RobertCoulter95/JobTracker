package com.example.jobtracker.user;

import com.example.jobtracker.company.Company;
import com.example.jobtracker.job.Job;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void createUser(User user) throws Exception {
        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser.isPresent()) {
            throw new IllegalStateException("Username taken.");
        }
        user.setCreated(LocalDateTime.now());
        userRepository.save(user);
    }
}
