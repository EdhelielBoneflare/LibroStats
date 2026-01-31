package me.librostats.libbase.exception;

import lombok.extern.slf4j.Slf4j;
import me.librostats.libbase.exception.ServerException.ErrorResponseDto;
import me.librostats.libbase.exception.ServerException.ServerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
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

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGenericException(Exception ex) {
        ErrorResponseDto error = new ErrorResponseDto(ex);
        log.error("Unhandled exception occurred: {}", ex.getMessage(), ex);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(error);
    }
}
