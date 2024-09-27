package team.fuyuki23.mate.adapter.in.rest.project.dto;

import jakarta.validation.constraints.NotEmpty;
import team.fuyuki23.mate.application.project.usecase.CreateProjectUseCase;
import team.fuyuki23.mate.domain.User;

public record PrivateCreateProjectRequest(@NotEmpty String identifier, @NotEmpty String name,
                                          String description) {

  public CreateProjectUseCase.Command toCommand(String slug, User user) {
    return new CreateProjectUseCase.Command(identifier, name, description, slug, user);
  }
}
