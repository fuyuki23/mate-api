package team.fuyuki23.mate.application.project.usecase;

import team.fuyuki23.mate.domain.Project;
import team.fuyuki23.mate.domain.User;

public interface CreateProjectUseCase {

  Result createProject(Command command);

  record Command(String identifier, String name, String description, String slug, User user) {

  }

  record Result(Project project) {

  }

}
