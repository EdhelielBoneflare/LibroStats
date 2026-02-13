package me.librostats.userservice.service;

import lombok.RequiredArgsConstructor;
import me.librostats.libbase.exception.UserException.UnauthorizedException;
import me.librostats.userservice.dto.RegRequest;
import me.librostats.userservice.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    public void checkPass(User user, String rawPassword) {
        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new UnauthorizedException("Invalid credentials");
        }
    }
}
