package me.librostats.userservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.librostats.userservice.dto.LoginRequest;
import me.librostats.userservice.dto.LoginResponse;
import me.librostats.userservice.dto.RegResponse;
import me.librostats.userservice.dto.RegRequest;
import me.librostats.userservice.model.User;
import me.librostats.userservice.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(BaseContr.PATH_API + BaseContr.PATH_AUTH)
@RequiredArgsConstructor
public class AuthContr {

    private final AuthService authService;

    @PostMapping(BaseContr.PATH_REGISTER)
    public ResponseEntity<RegResponse> register(@Valid @RequestBody RegRequest regReq) {
        User user = authService.registerUser(regReq);
        RegResponse resp = new RegResponse(
                user.getId(),
                user.getUsername()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    @PostMapping(BaseContr.PATH_LOGIN)
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginReq) {
        String token = authService.loginUser(loginReq);
        LoginResponse resp = new LoginResponse(token);

        return ResponseEntity.ok(resp);
    }
}
