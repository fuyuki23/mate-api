package team.fuyuki23.mate.application.common.port.in;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.fuyuki23.mate.application.common.port.out.FindProjectUsersByWorkspaceIdOutputPort;
import team.fuyuki23.mate.application.common.port.out.FindWorkspaceByIdOutputPort;
import team.fuyuki23.mate.application.common.usecase.ValidateWPUUseCase;
import team.fuyuki23.mate.common.exception.ApiException;
import team.fuyuki23.mate.common.exception.DefaultError;
import team.fuyuki23.mate.domain.ProjectUser;
import team.fuyuki23.mate.domain.User;
import team.fuyuki23.mate.domain.Workspace;
import team.fuyuki23.mate.domain.vo.WPU;

@Service
@RequiredArgsConstructor
public class ValidateWPUInputPort implements ValidateWPUUseCase {

  private final FindWorkspaceByIdOutputPort findWorkspaceByIdOutputPort;
  private final FindProjectUsersByWorkspaceIdOutputPort findProjectUsersByWorkspaceIdOutputPort;

  @Override
  public Result validateWPU(Command command) {
    Workspace workspace = null;
    List<ProjectUser> projectUsers = new ArrayList<>();
    User user = command.user();
    if (command.workspaceId() != null) {
      Optional<Workspace> maybeWorkspace = findWorkspaceByIdOutputPort.findWorkspaceById(
          command.workspaceId(), command.user().id());
      if (maybeWorkspace.isPresent()) {
        workspace = maybeWorkspace.get();
      } else {
        throw new ApiException(DefaultError.FORBIDDEN);
      }

      projectUsers = findProjectUsersByWorkspaceIdOutputPort.findProjectUsersByWorkspaceId(
          workspace.id(), command.user().id());
    }

    return new Result(new WPU(workspace, projectUsers, user));
  }
}
