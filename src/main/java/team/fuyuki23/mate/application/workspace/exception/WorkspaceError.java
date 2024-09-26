package team.fuyuki23.mate.application.workspace.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import team.fuyuki23.mate.common.exception.MateError;

@Getter
public enum WorkspaceError implements MateError {
  ALREADY_EXISTS(HttpStatus.CONFLICT, "api.workspace.already_exists", "Workspace already exists"),
  NOT_FOUND(HttpStatus.NOT_FOUND, "api.workspace.not_found", "Workspace not found"),
  ;

  private final int status;
  private final String code;
  private final String message;

  WorkspaceError(int status, String code, String message) {
    this.status = status;
    this.code = code;
    this.message = message;
  }

  WorkspaceError(HttpStatus status, String code, String message) {
    this.status = status.value();
    this.code = code;
    this.message = message;
  }

}
