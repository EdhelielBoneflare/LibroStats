package me.librostats.userservice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import me.librostats.userservice.util.Password;

@Getter
@Setter
@NoArgsConstructor
public class RegRequest {

    @NotNull
    @Size(min = 8, max = 32)
    private String username;

    @Password
    private String password;
}
