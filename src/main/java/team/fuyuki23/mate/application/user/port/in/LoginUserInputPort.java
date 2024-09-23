package team.fuyuki23.mate.application.user.port.in;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import team.fuyuki23.mate.application.user.port.out.FindAccountByEmailOutputPort;
import team.fuyuki23.mate.application.user.usecase.LoginUserUseCase;
import team.fuyuki23.mate.common.exception.ApiException;
import team.fuyuki23.mate.common.exception.DefaultError;
import team.fuyuki23.mate.common.jwt.JwtService;
import team.fuyuki23.mate.domain.Account;
import team.fuyuki23.mate.domain.User;
import team.fuyuki23.mate.entity.user.UserMapper;

@Service
@RequiredArgsConstructor
public class LoginUserInputPort implements LoginUserUseCase {

    private final FindAccountByEmailOutputPort findAccountByEmailOutputPort;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public Result login(Command command) throws ApiException {
        Account account = findAccountByEmailOutputPort.findAccountByEmail(command.email());
        if (account == null) {
            throw new ApiException(DefaultError.BAD_REQUEST);
        }

        if (!passwordEncoder.matches(command.password(), account.password())) {
            throw new ApiException(DefaultError.BAD_REQUEST);
        }

        User user = userMapper.toUser(account);
        return new Result(user, jwtService.generateTokens(user));
    }
}
