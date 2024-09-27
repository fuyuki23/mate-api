package team.fuyuki23.mate.application.project.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import team.fuyuki23.mate.common.exception.MateError;

@Getter
public enum ProjectError implements MateError {
  WORKSPACE_NOT_FOUND(HttpStatus.NOT_FOUND, "api.project.workspace_not_found",
      "Workspace not found"),
  ALREADY_EXISTS(HttpStatus.CONFLICT, "api.project.already_exists", "Project already exists"),
  NOT_FOUND(HttpStatus.NOT_FOUND, "api.project.not_found", "Project not found"),
  ;

  private final int status;
  private final String code;
  private final String message;

  ProjectError(int status, String code, String message) {
    this.status = status;
    this.code = code;
    this.message = message;
  }

  ProjectError(HttpStatus status, String code, String message) {
    this.status = status.value();
    this.code = code;
    this.message = message;
  }

}
