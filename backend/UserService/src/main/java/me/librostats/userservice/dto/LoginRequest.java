package me.librostats.userservice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.librostats.userservice.util.Password;

@Getter
@Setter
@NoArgsConstructor
public class LoginRequest {
    @NotNull
    @Size(min = 8, max = 32)
    private String username;

    @Password
    private String password;
}
