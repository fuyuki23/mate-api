package team.fuyuki23.mate.domain.vo;

import jakarta.validation.constraints.NotNull;
import java.util.List;
import team.fuyuki23.mate.domain.ProjectUser;
import team.fuyuki23.mate.domain.User;
import team.fuyuki23.mate.domain.Workspace;

public record WPU(Workspace workspace, List<ProjectUser> projectUsers, @NotNull User user) {

  public boolean hasWorkspace() {
    return workspace != null;
  }

  public boolean hasProject() {
    return this.hasWorkspace() && projectUsers != null && !projectUsers.isEmpty();
  }

  public boolean hasProject(String identifier) {
    return this.hasProject() && projectUsers.stream()
        .anyMatch(pu -> pu.project().identifier().equals(identifier));
  }
}
