package team.fuyuki23.mate.application.workspace.port.in;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.fuyuki23.mate.application.workspace.exception.WorkspaceError;
import team.fuyuki23.mate.application.workspace.port.out.FindWorkspaceBySlugAndUserIdOutputPort;
import team.fuyuki23.mate.application.workspace.usecase.FindWorkspaceBySlugUseCase;
import team.fuyuki23.mate.common.exception.ApiException;
import team.fuyuki23.mate.domain.Workspace;

@Service
@RequiredArgsConstructor
public class FindWorkspaceBySlugInputPort implements FindWorkspaceBySlugUseCase {

  private final FindWorkspaceBySlugAndUserIdOutputPort findWorkspaceBySlugAndUserIdOutputPort;

  @Override
  public Result findWorkspaceBySlug(Command command) {
    Workspace workspace = findWorkspaceBySlugAndUserIdOutputPort.findWorkspaceBySlugAndUserId(
            command.slug(), command.user().id())
        .orElseThrow(() -> new ApiException(WorkspaceError.NOT_FOUND));

    return new Result(workspace);
  }

}
