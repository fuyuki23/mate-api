package team.fuyuki23.mate.application.user.usecase;

import team.fuyuki23.mate.common.exception.ApiException;
import team.fuyuki23.mate.domain.User;
import team.fuyuki23.mate.domain.vo.Tokens;

public interface LoginUserUseCase {

  Result login(Command command) throws ApiException;

  record Command(String email, String password) {
  }

    record Result(User user, Tokens tokens) {
  }

}
