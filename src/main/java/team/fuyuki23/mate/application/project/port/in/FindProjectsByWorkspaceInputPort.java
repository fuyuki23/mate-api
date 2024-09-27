package team.fuyuki23.mate.application.project.port.in;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.fuyuki23.mate.application.project.exception.ProjectError;
import team.fuyuki23.mate.application.project.port.out.FindProjectsByWorkspaceIdOutputPort;
import team.fuyuki23.mate.application.project.port.out.FindWorkspaceBySlugOutputPort;
import team.fuyuki23.mate.application.project.usecase.FindProjectsByWorkspaceUseCase;
import team.fuyuki23.mate.common.exception.ApiException;
import team.fuyuki23.mate.domain.Project;
import team.fuyuki23.mate.domain.Workspace;

@Service
@RequiredArgsConstructor
public class FindProjectsByWorkspaceInputPort implements FindProjectsByWorkspaceUseCase {

  private final FindProjectsByWorkspaceIdOutputPort findProjectsByWorkspaceIdOutputPort;
  private final FindWorkspaceBySlugOutputPort findWorkspaceBySlugOutputPort;

  @Override
  public Result findProjectsByWorkspace(Command command) {
    Workspace workspace = findWorkspaceBySlugOutputPort.findWorkspaceBySlug(command.slug())
        .orElseThrow(() -> new ApiException(ProjectError.WORKSPACE_NOT_FOUND));

    List<Project> projects = findProjectsByWorkspaceIdOutputPort.findProjectsByWorkspaceId(
        workspace.id());

    return new Result(projects);
  }

}
