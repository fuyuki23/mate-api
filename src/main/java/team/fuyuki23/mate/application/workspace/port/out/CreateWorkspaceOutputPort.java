package team.fuyuki23.mate.application.workspace.port.out;

import team.fuyuki23.mate.domain.User;
import team.fuyuki23.mate.domain.Workspace;

public interface CreateWorkspaceOutputPort {

  Workspace createWorkspace(String name, String slug, int size, User requester);

}
