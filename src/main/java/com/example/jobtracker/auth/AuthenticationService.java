package com.example.jobtracker.auth;

import com.example.jobtracker.config.JwtService;
import com.example.jobtracker.enums.Role;
import com.example.jobtracker.user.PasswordResetToken;
import com.example.jobtracker.user.PasswordResetTokenRepository;
import com.example.jobtracker.user.User;
import com.example.jobtracker.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final PasswordResetTokenRepository PRTRepository;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request){
        var user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(encodePassword(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request){
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    request.getUsername(),
                    request.getPassword()
            )
        );
        User user = userRepository.findByUsername(request.getUsername())
            .orElseThrow();
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
            .token(jwtToken)
            .build();
    }

    public String encodePassword(String password){
        return passwordEncoder.encode(password);
    }

    public void recoverAccount(String email) {
        Optional<User> potentialUser = userRepository.findByEmail(email);
        if (potentialUser.isPresent()){
            String token = jwtService.generateToken(potentialUser.get());
            System.out.println(token);
            PasswordResetToken myToken = new PasswordResetToken(encodePassword(token), potentialUser.get().getUsername());
            myToken.setExpiryDate(LocalDateTime.now().plusSeconds(PasswordResetToken.EXPIRATION));
            PRTRepository.save(myToken);
        }
    }
    public void changePassword(String token, String currentPassword,String username,String newPassword) {
        Optional<User> user = userRepository.findByUsername(username);
        boolean updatePassword = false;
        if (user.isPresent()){
            if (token != null) {
                Optional<PasswordResetToken> PasswordResetToken = PRTRepository.findByUsername(user.get().getUsername());
                if (PasswordResetToken.isPresent() && !PasswordResetToken.get().isExpired() && passwordEncoder.matches(token,PasswordResetToken.get().getToken())){
                    updatePassword = true;
                }
            } else if (currentPassword != null){
                if (passwordEncoder.matches(currentPassword,user.get().getPassword())) {
                    updatePassword = true;
                }
            }
        }
        if (updatePassword){
            PRTRepository.deleteAllByUsername(user.get().getUsername());
            user.get().setPassword(encodePassword(newPassword));
        }
        //todo WILL FAIL until failure response is handled
        userRepository.save(user.get());
    }

}
