package team.fuyuki23.mate.common.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import team.fuyuki23.mate.common.exception.ApiException;
import team.fuyuki23.mate.common.exception.DefaultError;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class MateRestControllerAdvice {

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<?> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(DefaultError.METHOD_NOT_ALLOWED.toMap());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(DefaultError.BAD_REQUEST.toMap());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> httpMessageNotReadableException(HttpMessageNotReadableException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(DefaultError.BAD_REQUEST.toMap());
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<?> noResourceFoundException(NoResourceFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(DefaultError.NOT_FOUND.toMap());
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<?> apiException(ApiException e) {
        return e.toResponse();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> unknownException(Exception e) {
        log.error("Unknown error", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(DefaultError.INTERNAL_SERVER_ERROR.toMap());
    }

}
