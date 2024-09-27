package team.fuyuki23.mate.application.user.usecase;

import team.fuyuki23.mate.domain.User;
import team.fuyuki23.mate.domain.vo.Tokens;

public interface LoginUserUseCase {

  Result login(Command command);

  record Command(String email, String password) {
  }

    record Result(User user, Tokens tokens) {
  }

}
