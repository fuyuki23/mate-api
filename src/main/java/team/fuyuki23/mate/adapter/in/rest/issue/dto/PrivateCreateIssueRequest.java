package team.fuyuki23.mate.adapter.in.rest.issue.dto;

import jakarta.validation.constraints.NotBlank;
import team.fuyuki23.mate.application.issue.usecase.CreateIssueUseCase;
import team.fuyuki23.mate.domain.vo.SI;

public record PrivateCreateIssueRequest(
    @NotBlank
    String title,
    String description) {

  public CreateIssueUseCase.Command toCommand(SI si) {
    return new CreateIssueUseCase.Command(si, this.title(), this.description());
  }

}
