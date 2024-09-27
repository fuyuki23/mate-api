package team.fuyuki23.mate.application.user.usecase;

public interface RegisterUserUseCase {

  Result register(Command command);

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
