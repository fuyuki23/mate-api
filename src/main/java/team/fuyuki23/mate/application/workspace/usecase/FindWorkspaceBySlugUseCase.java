package team.fuyuki23.mate.application.workspace.usecase;

import team.fuyuki23.mate.domain.User;
import team.fuyuki23.mate.domain.Workspace;

public interface FindWorkspaceBySlugUseCase {

  Result findWorkspaceBySlug(Command command);

  record Command(String slug, User user) {

  }

  record Result(Workspace workspace) {

  }

}
