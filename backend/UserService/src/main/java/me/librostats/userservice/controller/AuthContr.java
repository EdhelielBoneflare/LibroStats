package me.librostats.userservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.librostats.userservice.dto.RegAnswer;
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
@RequestMapping(BaseContr.PATH_USER_SERVICE + BaseContr.PATH_API + BaseContr.PATH_AUTH)
@RequiredArgsConstructor
public class AuthContr {

    private final AuthService authService;

    @PostMapping(BaseContr.PATH_REGISTER)
    public ResponseEntity<RegAnswer> register(@Valid @RequestBody RegRequest regReq) {
        User user = authService.registerUser(regReq);
        RegAnswer resp = new RegAnswer(
                user.getId(),
                user.getUsername()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }
}
