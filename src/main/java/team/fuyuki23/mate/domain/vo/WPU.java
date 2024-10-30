package team.fuyuki23.mate.domain.vo;

import jakarta.validation.constraints.NotNull;
import team.fuyuki23.mate.domain.Project;
import team.fuyuki23.mate.domain.User;
import team.fuyuki23.mate.domain.Workspace;

public record WPU(Workspace workspace, Project project, @NotNull User user) {

  public boolean hasWorkspace() {
    return workspace != null;
  }

  public boolean hasProject() {
    return this.hasWorkspace() && project != null;
  }
}
