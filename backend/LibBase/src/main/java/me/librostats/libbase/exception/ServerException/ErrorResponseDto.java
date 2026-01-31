package me.librostats.libbase.exception.ServerException;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class ErrorResponseDto {
    @JsonProperty("error_id")
    private final String errorId;
    @JsonProperty("message")
    private final String message;

    public ErrorResponseDto(ServerException e) {
        this.errorId = e.getId();
        this.message = e.getUserMessage() != null ? e.getUserMessage() : e.getMessage();
    }

    public ErrorResponseDto(Throwable e) {
        this.errorId = UUID.randomUUID().toString();
        this.message = "Internal error";
    }
}
