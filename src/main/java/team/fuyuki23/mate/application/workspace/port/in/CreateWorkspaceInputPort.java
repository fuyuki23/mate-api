package team.fuyuki23.mate.application.workspace.port.in;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.fuyuki23.mate.application.workspace.exception.WorkspaceError;
import team.fuyuki23.mate.application.workspace.port.out.CreateWorkspaceOutputPort;
import team.fuyuki23.mate.application.workspace.port.out.FindWorkspaceByNameOrSlugOutputPort;
import team.fuyuki23.mate.application.workspace.usecase.CreateWorkspaceUseCase;
import team.fuyuki23.mate.common.exception.ApiException;
import team.fuyuki23.mate.domain.Workspace;

@Service
@RequiredArgsConstructor
public class CreateWorkspaceInputPort implements CreateWorkspaceUseCase {

  private final FindWorkspaceByNameOrSlugOutputPort findWorkspaceByNameOrSlugOutputPort;
  private final CreateWorkspaceOutputPort createWorkspaceOutputPort;

  // TODO: Move this to a configuration file
  private final int DEFAULT_WORKSPACE_SIZE = 10;

  @Override
  public Result create(Command command) throws ApiException {
    Optional<Workspace> maybeWorkspace = findWorkspaceByNameOrSlugOutputPort.findWorkspaceByNameOrSlug(
        command.name(), command.slug());
    if (maybeWorkspace.isPresent()) {
      throw new ApiException(WorkspaceError.ALREADY_EXISTS);
    }

    Workspace createdWorkspace = createWorkspaceOutputPort.createWorkspace(
        command.name(),
        command.slug(),
        this.DEFAULT_WORKSPACE_SIZE,
        command.requester()
    );

    return new Result(createdWorkspace);
  }
}
