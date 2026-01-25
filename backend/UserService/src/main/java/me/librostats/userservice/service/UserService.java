package me.librostats.userservice.service;

import lombok.RequiredArgsConstructor;
import me.librostats.userservice.dto.RegRequest;
import me.librostats.userservice.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;

    public User createUser(RegRequest regReq) {
        User user = new User();
        user.setUsername(regReq.getUsername());
        user.setPassword(passwordEncoder.encode(regReq.getPassword()));
        user.setRole(User.ROLE_USER);

        return user;
    }
}
