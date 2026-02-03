package me.librostats.libbase.exception;

import lombok.extern.slf4j.Slf4j;
import me.librostats.libbase.exception.ServerException.ErrorResponseDto;
import me.librostats.libbase.exception.ServerException.ServerException;
import me.librostats.libbase.exception.UserException.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RestControllerAdvice
@Slf4j
public class ExceptHandler {

    @ExceptionHandler(ServerException.class)
    public ResponseEntity<ErrorResponseDto> handleServerException(ServerException ex) {
        ErrorResponseDto error = new ErrorResponseDto(ex);
        log.error("Error {} occurred: {} [] [{}] : {}",
                ex.getType(), ex.getId(), ex.getErrCode(),  ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(error);
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<UserErrorDto> handleServerException(UserException ex) {
        UserErrorDto error = new UserErrorDto(ex.getMessage());

        HttpStatus status;
        status = switch (ex) {
            case AlreadyExistsException e-> HttpStatus.CONFLICT;
            case ValidationException e -> HttpStatus.BAD_REQUEST;
            case ResourceNotFoundException e -> HttpStatus.NOT_FOUND;
            case UnauthorizedException e -> {
                log.warn("Unauthorized access attempt: {}", e.getMessage());
                yield HttpStatus.UNAUTHORIZED;
            }
            default -> HttpStatus.BAD_REQUEST;
        };

        return ResponseEntity
                .status(status)
                .body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGenericException(Exception ex) {
        ErrorResponseDto error = new ErrorResponseDto(ex);
        log.error("Unhandled exception occurred: {}", ex.getMessage(), ex);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(error);
    }
}
