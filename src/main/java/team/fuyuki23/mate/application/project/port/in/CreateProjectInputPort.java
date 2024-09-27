package team.fuyuki23.mate.application.project.port.in;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.fuyuki23.mate.application.common.port.in.ValidateWorkspaceInputPort;
import team.fuyuki23.mate.application.project.exception.ProjectError;
import team.fuyuki23.mate.application.project.port.out.CreateProjectOutputPort;
import team.fuyuki23.mate.application.project.port.out.FindProjectByIdentifierInWorkspaceOutputPort;
import team.fuyuki23.mate.application.project.usecase.CreateProjectUseCase;
import team.fuyuki23.mate.common.exception.ApiException;
import team.fuyuki23.mate.domain.Member;
import team.fuyuki23.mate.domain.Project;
import team.fuyuki23.mate.domain.vo.SlugAndUser;

@Service
@RequiredArgsConstructor
public class CreateProjectInputPort implements CreateProjectUseCase {

  private final ValidateWorkspaceInputPort validateWorkspaceInputPort;
  private final FindProjectByIdentifierInWorkspaceOutputPort findProjectByIdentifierInWorkspaceOutputPort;
  private final CreateProjectOutputPort createProjectOutputPort;

  @Override
  public Result createProject(Command command) {
    Member member = validateWorkspaceInputPort.validateWorkspace(
        new SlugAndUser(
            command.slug(),
            command.user().id()
        )
    );

    Optional<Project> maybeProject = findProjectByIdentifierInWorkspaceOutputPort.findProjectByIdentifierInWorkspace(
        command.identifier(), member.workspace()
            .id());
    if (maybeProject.isPresent()) {
      throw new ApiException(ProjectError.ALREADY_EXISTS);
    }

    Project createdProject = createProjectOutputPort.createProject(
        command.name(),
        command.identifier(),
        command.description(),
        member.workspace(),
        member.user()
    );

    return new Result(createdProject);
  }
}
