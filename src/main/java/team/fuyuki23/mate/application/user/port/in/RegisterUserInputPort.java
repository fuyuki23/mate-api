package team.fuyuki23.mate.application.user.port.in;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import team.fuyuki23.mate.application.user.exception.UserError;
import team.fuyuki23.mate.application.user.port.out.FindUserByEmailOutputPort;
import team.fuyuki23.mate.application.user.port.out.WriteAccountOutputPort;
import team.fuyuki23.mate.application.user.usecase.RegisterUserUseCase;
import team.fuyuki23.mate.common.exception.ApiException;
import team.fuyuki23.mate.domain.Account;
import team.fuyuki23.mate.domain.User;

@Service
@RequiredArgsConstructor
public class RegisterUserInputPort implements RegisterUserUseCase {

    private final FindUserByEmailOutputPort findUserByEmailOutputPort;
    private final WriteAccountOutputPort writeAccountOutputPort;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Result register(Command command) {
        User existingUser = findUserByEmailOutputPort.findUserByEmail(command.email());
        if (existingUser != null) {
            throw new ApiException(UserError.ALREADY_EXISTS);
        }

        String password = passwordEncoder.encode(command.password());
        Account savedAccount = writeAccountOutputPort.writeAccount(
                new Account(
                        null,
                        command.email(),
                        password,
                        command.firstName(),
                        command.lastName()
                )
        );

        return new Result(
                savedAccount != null
        );
    }

}
