package org.example.user.service;

import lombok.RequiredArgsConstructor;
import org.example.user.model.User;
import org.example.user.repository.UserRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Profile("!test")
@Service
public class AppUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    public AppUserDetailsService(UserRepository userRepository) { this.userRepository = userRepository; }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var u = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));

        var pwd = java.util.Optional.ofNullable(u.getPasswordHash()).orElse("");
        return new org.springframework.security.core.userdetails.User(
                u.getEmail(),
                pwd,
                java.util.List.of()  // or ROLE_USER
        );
    }
}
