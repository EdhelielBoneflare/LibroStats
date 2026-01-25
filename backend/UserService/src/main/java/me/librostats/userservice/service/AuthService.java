package me.librostats.userservice.service;

import lombok.RequiredArgsConstructor;
import me.librostats.userservice.dto.RegRequest;
import me.librostats.userservice.model.User;
import me.librostats.userservice.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepo;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    //private final AuthenticationManager authManager;

    @Transactional
    public User registerUser(RegRequest regReq) {
        if (userRepo.existsByUsername(regReq.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }

        User user = userService.createUser(regReq);
        return userRepo.save(user);
    }
}
