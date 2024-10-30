package team.fuyuki23.mate.application.common.port.in;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.fuyuki23.mate.application.common.port.out.FindWorkspaceBySlugOutputPort;
import team.fuyuki23.mate.application.common.usecase.ValidateWPUUseCase;
import team.fuyuki23.mate.domain.Project;
import team.fuyuki23.mate.domain.User;
import team.fuyuki23.mate.domain.Workspace;
import team.fuyuki23.mate.domain.vo.WPU;

@Service
@RequiredArgsConstructor
public class ValidateWPUInputPort implements ValidateWPUUseCase {

  private final FindWorkspaceBySlugOutputPort findWorkspaceBySlugOutputPort;

  @Override
  public Result validateWPU(Command command) {
    Workspace workspace = null;
    Project project = null;
    User user = null;
    if (command.slug() != null) {
      Optional<Workspace> maybeWorkspace = findWorkspaceBySlugOutputPort.findWorkspaceBySlug(
          command.slug(), command.user().id());
      if (maybeWorkspace.isPresent()) {
        workspace = maybeWorkspace.get();
      }

      if (command.identifier() != null) {

      }
    }

    return new Result(new WPU(workspace, project, user));
  }
}
