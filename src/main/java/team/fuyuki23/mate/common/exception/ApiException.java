package team.fuyuki23.mate.common.exception;

import lombok.Getter;
import org.springframework.http.ResponseEntity;

import java.util.Map;

@Getter
public class ApiException extends Exception {

  private final int status;
  private final String code;
  private final String message;

  public ApiException(MateError error) {
    super(error.getMessage());
    this.status = error.getStatus();
    this.code = error.getCode();
    this.message = error.getMessage();
  }

  public ResponseEntity<?> toResponse() {
    return ResponseEntity.status(this.status).body(Map.of(
            "code", this.code,
            "message", this.message
    ));
  }

}
