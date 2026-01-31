package me.librostats.libbase.exception.ServerException;

import lombok.Getter;

import java.util.UUID;

@Getter
public class ServerException extends RuntimeException {
    private String id;
    private String errCode;
    private ErrorType type;
    private Throwable cause;
    private String userMessage;

    public ServerException(String errCode, ErrorType type, String message) {
        super(message);
        this.id = UUID.randomUUID().toString();
        this.errCode = errCode;
        this.type = type;
    }

    public ServerException(String errCode, ErrorType type, String message, String userMessage) {
        super(message);
        this.id = UUID.randomUUID().toString();
        this.errCode = errCode;
        this.type = type;
        this.userMessage = userMessage;
    }

    public ServerException(String errCode, ErrorType type, String message, Throwable e) {
        super(message);
        this.id = UUID.randomUUID().toString();
        this.errCode = errCode;
        this.type = type;
        this.cause = e;
    }

    public ServerException(String errCode, ErrorType type, String message, String userMessage, Throwable e) {
        super(message);
        this.id = UUID.randomUUID().toString();
        this.errCode = errCode;
        this.type = type;
        this.userMessage = userMessage;
        this.cause = e;
    }
}
