package team.fuyuki23.mate.application.token.usecase;

import java.util.UUID;
import team.fuyuki23.mate.domain.User;

public interface ValidateAuthnUseCase {

  Result validateAuthn(Command command);

  record Command(UUID userId) {

  }

  record Result(boolean isValid, User user) {

  }

}
