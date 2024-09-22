package team.fuyuki23.mate.adapter.in.rest.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class PublicUserRequest {

  public record LoginDto(
      @NotEmpty
      @Email
      String email,
      @NotEmpty
      String password
  ) {

  }

}
