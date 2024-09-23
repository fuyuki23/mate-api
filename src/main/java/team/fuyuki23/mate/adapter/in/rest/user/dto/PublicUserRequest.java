package team.fuyuki23.mate.adapter.in.rest.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import team.fuyuki23.mate.application.user.usecase.LoginUserUseCase;
import team.fuyuki23.mate.application.user.usecase.RegisterUserUseCase;

public class PublicUserRequest {

    public record LoginDto(@NotEmpty @Email String email, @NotEmpty String password) {
        public LoginUserUseCase.Command toCommand() {
            return new LoginUserUseCase.Command(email, password);
        }
    }

    public record RegisterDto(
            @NotEmpty @Email String email,
            @NotEmpty String password,
            String firstName,
            @NotEmpty String lastName
    ) {
        public RegisterUserUseCase.Command toCommand() {
            return new RegisterUserUseCase.Command(email, password, firstName, lastName);
        }
    }

}
