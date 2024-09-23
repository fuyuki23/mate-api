package team.fuyuki23.mate.application.token.usecase;

import team.fuyuki23.mate.domain.User;

import java.util.UUID;

public interface ValidateAuthnUseCase {

    Result validateAuthn(Command command);

    record Command(UUID userId) {
    }

    record Result(boolean isValid, User user) {
    }

}
