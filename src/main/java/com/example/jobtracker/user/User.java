package com.example.jobtracker.user;

import com.example.jobtracker.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity(name = "Users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table
public class User implements UserDetails {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;
    private String email;
    private LocalDateTime created;
    @Enumerated(EnumType.ORDINAL)
    private Role role;
    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
