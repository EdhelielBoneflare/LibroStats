package me.librostats.userservice.service;

import lombok.RequiredArgsConstructor;
import me.librostats.libbase.exception.UserException.AlreadyExistsException;
import me.librostats.libbase.exception.UserException.ResourceNotFoundException;
import me.librostats.userservice.dto.LoginRequest;
import me.librostats.userservice.dto.RegRequest;
import me.librostats.userservice.model.User;
import me.librostats.userservice.repository.UserRepository;
import me.librostats.userservice.util.JwtUtil;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@ComponentScan
public class AuthService {

    private final UserRepository userRepo;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @Transactional
    public User registerUser(RegRequest regReq) {
        if (userRepo.existsByUsername(regReq.getUsername())) {
            throw new AlreadyExistsException("Username is already taken");
        }

        User user = userService.createUser(regReq);
        return userRepo.save(user);
    }

    public String loginUser(LoginRequest loginReq) {
        User user = userRepo.findByUsername(loginReq.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User %s not found".formatted(loginReq.getUsername())));

        userService.checkPass(user, loginReq.getPassword());

        return jwtUtil.generateToken(user);
    }

}
