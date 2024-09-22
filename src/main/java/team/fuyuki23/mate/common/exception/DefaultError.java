package team.fuyuki23.mate.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum DefaultError implements MateError {
  BAD_REQUEST(400, "api.common.bad_request", "Bad Request"),
  UNAUTHORIZED(401, "api.common.unauthorized", "Unauthorized"),
  FORBIDDEN(403, "api.common.forbidden", "Forbidden"),
  NOT_FOUND(404, "api.common.not_found", "Not Found"),
  METHOD_NOT_ALLOWED(405, "api.common.method_not_allowed", "Method Not Allowed"),
  CONFLICT(409, "api.common.conflict", "Conflict"),
  INTERNAL_SERVER_ERROR(500, "api.common.internal_server_error", "Internal Server Error")
  ;

  private final int status;
  private final String code;
  private final String message;

  DefaultError(int status, String code, String message) {
    this.status = status;
    this.code = code;
    this.message = message;
  }

  DefaultError(HttpStatus status, String code, String message) {
    this.status = status.value();
    this.code = code;
    this.message = message;
  }
}
