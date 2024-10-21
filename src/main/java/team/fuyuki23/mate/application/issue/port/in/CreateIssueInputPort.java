package team.fuyuki23.mate.application.issue.port.in;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.fuyuki23.mate.application.issue.port.out.CreateIssueOutputPort;
import team.fuyuki23.mate.application.issue.usecase.CreateIssueUseCase;

@Service
@RequiredArgsConstructor
public class CreateIssueInputPort implements CreateIssueUseCase {

  private final CreateIssueOutputPort createIssueOutputPort;

  @Override
  public Result createIssue(Command command) {
//    createIssueOutputPort.createIssue(
//        command.title(),
//        command.description()
//    )
    return null;
  }
}
