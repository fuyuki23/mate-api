package team.fuyuki23.mate.application.user.port.in;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.fuyuki23.mate.application.user.port.out.FindUserByEmailOutputPort;
import team.fuyuki23.mate.application.user.usecase.LoginUserUseCase;
import team.fuyuki23.mate.common.exception.ApiException;
import team.fuyuki23.mate.common.exception.DefaultError;
import team.fuyuki23.mate.domain.User;

@Service
@RequiredArgsConstructor
public class LoginUserInputPort implements LoginUserUseCase {

  private final FindUserByEmailOutputPort findUserByEmailOutputPort;

  @Override
  public Result login(Command command) throws ApiException {
    User user = findUserByEmailOutputPort.findUserByEmail(command.email());
    if (user != null) {
      throw new ApiException(DefaultError.NOT_FOUND);
    }

    return null;
  }
}
