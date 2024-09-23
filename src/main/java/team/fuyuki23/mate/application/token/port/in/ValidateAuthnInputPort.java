package team.fuyuki23.mate.application.token.port.in;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.fuyuki23.mate.application.token.port.out.FindUserByIdOutputPort;
import team.fuyuki23.mate.application.token.usecase.ValidateAuthnUseCase;
import team.fuyuki23.mate.domain.User;

@Service
@RequiredArgsConstructor
public class ValidateAuthnInputPort implements ValidateAuthnUseCase {

    private final FindUserByIdOutputPort findUserByIdOutputPort;

    @Override
    public Result validateAuthn(Command command) {
        User user = findUserByIdOutputPort.findUserById(command.userId());
        return new Result(user != null, user);
    }

}
