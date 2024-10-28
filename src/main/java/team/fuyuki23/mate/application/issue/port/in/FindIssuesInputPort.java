package team.fuyuki23.mate.application.issue.port.in;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import team.fuyuki23.mate.application.common.usecase.ValidateProjectUseCase;
import team.fuyuki23.mate.application.issue.port.out.FindIssuesWithPageOutputPort;
import team.fuyuki23.mate.application.issue.usecase.FindIssuesUseCase;
import team.fuyuki23.mate.domain.ProjectUser;

@Service
@RequiredArgsConstructor
public class FindIssuesInputPort implements FindIssuesUseCase {

  private final ValidateProjectUseCase validateProjectUseCase;
  private final FindIssuesWithPageOutputPort findIssuesWithPageOutputPort;

  @Override
  public Result findIssues(Command command) {
    ProjectUser projectUser = validateProjectUseCase.validateProject(command.si().slug(),
        command.si().identifier(), command.user().id());

    return new Result(findIssuesWithPageOutputPort.findIssuesWithPage(projectUser.workspace().id(),
        projectUser.project().id(), PageRequest.of(command.page(), 10)));
  }
}
