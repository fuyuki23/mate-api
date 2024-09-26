package team.fuyuki23.mate.application.member.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import team.fuyuki23.mate.common.exception.MateError;

@Getter
public enum MemberError implements MateError {
  ALREADY_EXISTS(HttpStatus.CONFLICT, "api.member.already_exists", "Member already exists"),
  NOT_FOUND(HttpStatus.NOT_FOUND, "api.member.not_found", "Member not found"),
  ;

  private final int status;
  private final String code;
  private final String message;

  MemberError(int status, String code, String message) {
    this.status = status;
    this.code = code;
    this.message = message;
  }

  MemberError(HttpStatus status, String code, String message) {
    this.status = status.value();
    this.code = code;
    this.message = message;
  }

}
