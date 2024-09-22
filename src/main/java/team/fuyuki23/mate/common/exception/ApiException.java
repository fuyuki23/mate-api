package team.fuyuki23.mate.common.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

@Getter
public class ApiException extends Throwable {

  @JsonIgnore
  private final int status;
  private final String code;
  private final String message;

  public ApiException(MateError error) {
    super(error.getMessage());
    this.status = error.getStatus();
    this.code = error.getCode();
    this.message = error.getMessage();
  }

}
