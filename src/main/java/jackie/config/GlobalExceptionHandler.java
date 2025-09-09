package jackie.config;

import jackie.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<?> handleBaseException(BaseException e) {
        return ResponseEntity
                .status(e.getStatus())
                .body(Map.of(
                        "status", e.getStatus(),
                        "message", e.getMessage(),
                        "timestamp", LocalDateTime.now().toString()
                ));
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleOrderException(Exception e) {
        return ResponseEntity
                .internalServerError()
                .body(Map.of(
                        "status", 500,
                        "message", e.getMessage(),
                        "timestamp", LocalDateTime.now().toString()
                ));
    }
}