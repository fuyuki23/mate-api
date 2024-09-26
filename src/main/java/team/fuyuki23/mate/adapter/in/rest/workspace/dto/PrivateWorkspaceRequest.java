package team.fuyuki23.mate.adapter.in.rest.workspace.dto;

import jakarta.validation.constraints.NotEmpty;
import team.fuyuki23.mate.application.workspace.usecase.CreateWorkspaceUseCase;
import team.fuyuki23.mate.domain.User;

public class PrivateWorkspaceRequest {

  public record CreateDto(@NotEmpty String name, @NotEmpty String slug) {

    public CreateWorkspaceUseCase.Command toCommand(User user) {
      return new CreateWorkspaceUseCase.Command(name, slug, user);
    }
  }

}
