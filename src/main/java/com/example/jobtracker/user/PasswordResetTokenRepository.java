package com.example.jobtracker.user;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken,Long> {
    Optional<PasswordResetToken> findByUsername(String username);

    void deleteAllByUsername(String username);
}
