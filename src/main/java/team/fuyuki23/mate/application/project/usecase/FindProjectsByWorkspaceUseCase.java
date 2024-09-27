package team.fuyuki23.mate.application.project.usecase;

import java.util.List;
import team.fuyuki23.mate.domain.Project;
import team.fuyuki23.mate.domain.User;

public interface FindProjectsByWorkspaceUseCase {

  Result findProjectsByWorkspace(Command command);

  record Command(String slug, User user) {

  }

  record Result(List<Project> projects) {

  }

}
