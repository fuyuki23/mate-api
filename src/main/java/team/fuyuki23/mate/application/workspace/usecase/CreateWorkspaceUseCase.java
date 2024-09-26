package team.fuyuki23.mate.application.workspace.usecase;

import team.fuyuki23.mate.common.exception.ApiException;
import team.fuyuki23.mate.domain.User;
import team.fuyuki23.mate.domain.Workspace;

public interface CreateWorkspaceUseCase {

  Result create(Command command) throws ApiException;

  record Command(String name, String slug, User requester) {

  }

  record Result(Workspace workspace) {

  }

}
