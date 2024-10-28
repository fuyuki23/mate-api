package team.fuyuki23.mate.adapter.in.rest.issue.dto;

import jakarta.validation.constraints.NotBlank;
import java.util.List;
import java.util.UUID;
import team.fuyuki23.mate.application.issue.usecase.CreateIssueUseCase;
import team.fuyuki23.mate.domain.User;
import team.fuyuki23.mate.domain.vo.SI;

public record PrivateCreateIssueRequest(
    @NotBlank
    String title,
    String description,
    List<UUID> assigneeIds) {

  public CreateIssueUseCase.Command toCommand(SI si, User requester) {
    return new CreateIssueUseCase.Command(si, this.title(), this.description(), requester);
  }

}
