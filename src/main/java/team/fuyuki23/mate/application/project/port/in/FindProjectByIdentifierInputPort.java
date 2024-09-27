package team.fuyuki23.mate.application.project.port.in;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.fuyuki23.mate.application.common.port.in.ValidateWorkspaceInputPort;
import team.fuyuki23.mate.application.project.exception.ProjectError;
import team.fuyuki23.mate.application.project.port.out.FindProjectByIdentifierInWorkspaceOutputPort;
import team.fuyuki23.mate.application.project.usecase.FindProjectByIdentifierUseCase;
import team.fuyuki23.mate.common.exception.ApiException;
import team.fuyuki23.mate.domain.Member;
import team.fuyuki23.mate.domain.Project;
import team.fuyuki23.mate.domain.vo.SlugAndUser;

@Service
@RequiredArgsConstructor
public class FindProjectByIdentifierInputPort implements FindProjectByIdentifierUseCase {

  private final ValidateWorkspaceInputPort validateWorkspaceInputPort;
  private final FindProjectByIdentifierInWorkspaceOutputPort findProjectByIdentifierInWorkspaceOutputPort;

  @Override
  public Result findProjectByIdentifier(Command command) {
    Member member = validateWorkspaceInputPort.validateWorkspace(
        new SlugAndUser(command.slug(), command.user().id()));

    Project project = findProjectByIdentifierInWorkspaceOutputPort.findProjectByIdentifierInWorkspace(
            command.identifier(), member.workspace().id())
        .orElseThrow(() -> new ApiException(ProjectError.NOT_FOUND));

    return new Result(project);
  }

}
