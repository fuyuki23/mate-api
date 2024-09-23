package team.fuyuki23.mate.adapter.in.rest.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.fuyuki23.mate.adapter.in.rest.user.dto.PublicUserRequest;
import team.fuyuki23.mate.application.user.usecase.LoginUserUseCase;
import team.fuyuki23.mate.application.user.usecase.RegisterUserUseCase;
import team.fuyuki23.mate.common.exception.ApiException;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class PublicUserRestAdapter {

  private final LoginUserUseCase loginUserUseCase;
  private final RegisterUserUseCase registerUserUseCase;

  @PostMapping("/login")
  public ResponseEntity<?> Login(@Valid @RequestBody PublicUserRequest.LoginDto payload) throws ApiException {
    return ResponseEntity.ok().body(loginUserUseCase.login(payload.toCommand()));
  }

  @PostMapping("/register")
  public ResponseEntity<?> Register(@Valid @RequestBody PublicUserRequest.RegisterDto payload) throws ApiException {
    return ResponseEntity.ok().body(registerUserUseCase.register(payload.toCommand()));
  }

}
