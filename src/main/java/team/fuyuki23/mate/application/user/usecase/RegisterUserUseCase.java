package team.fuyuki23.mate.application.user.usecase;

import team.fuyuki23.mate.common.exception.ApiException;

public interface RegisterUserUseCase {

    Result register(Command command) throws ApiException;

    record Command(
            String email,
            String password,
            String firstName,
            String lastName
    ) {
    }

    record Result(
            boolean isRegistered
    ) {
    }

}
