package team.fuyuki23.mate.common.exception;

import java.util.Map;

public interface MateError {

  int getStatus();
  String getCode();
  String getMessage();

  default Map<String, String> toMap() {
    return Map.of(
        "code", getCode(),
        "message", getMessage()
    );
  }

}
