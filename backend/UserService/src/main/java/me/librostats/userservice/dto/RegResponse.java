package me.librostats.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegResponse {
    private String userId;
    private String username;
}
