package team.fuyuki23.mate.application.user.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import team.fuyuki23.mate.common.exception.MateError;

@Getter
public enum UserError implements MateError {
    ALREADY_EXISTS(HttpStatus.CONFLICT, "api.user.already_exists", "User already exists"),
    NOT_FOUND(HttpStatus.NOT_FOUND, "api.user.not_found", "User not found"),
    ;

    private final int status;
    private final String code;
    private final String message;

    UserError(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    UserError(HttpStatus status, String code, String message) {
        this.status = status.value();
        this.code = code;
        this.message = message;
    }

}
