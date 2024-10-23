package team.fuyuki23.mate.application.issue.port.in;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.fuyuki23.mate.application.common.usecase.ValidateProjectUseCase;
import team.fuyuki23.mate.application.issue.port.out.CreateIssueOutputPort;
import team.fuyuki23.mate.application.issue.port.out.FindIssueStateByNameOutputPort;
import team.fuyuki23.mate.application.issue.usecase.CreateIssueUseCase;
import team.fuyuki23.mate.domain.Issue;
import team.fuyuki23.mate.domain.ProjectUser;

@Service
@RequiredArgsConstructor
public class CreateIssueInputPort implements CreateIssueUseCase {

  private final ValidateProjectUseCase validateProjectUseCase;
  private final CreateIssueOutputPort createIssueOutputPort;
  private final FindIssueStateByNameOutputPort findIssueStateByNameOutputPort;

  @Override
  public Result createIssue(Command command) {
    ProjectUser projectUser = validateProjectUseCase.validateProject(command.si().slug(),
        command.si().identifier(), command.requester().id());

    Issue createdIssue = createIssueOutputPort.createIssue(
        command.title(),
        command.description(),
        projectUser.workspace(),
        projectUser.project(),
        projectUser.user()
    );
    return new Result(
        createdIssue.id()
    );
  }
}
