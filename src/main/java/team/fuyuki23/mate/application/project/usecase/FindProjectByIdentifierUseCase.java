package team.fuyuki23.mate.application.project.usecase;

import team.fuyuki23.mate.domain.Project;
import team.fuyuki23.mate.domain.User;

public interface FindProjectByIdentifierUseCase {

  Result findProjectByIdentifier(Command command);

  record Command(String slug, String identifier, User user) {

  }

  record Result(Project project) {

  }

}
