package team.fuyuki23.mate.application.workspace.usecase;

import team.fuyuki23.mate.domain.User;
import team.fuyuki23.mate.domain.Workspace;

import java.util.List;

public interface FindWorkspacesByUserUseCase {

    Result findWorkspacesByUser(Command command);

    record Command(
            User user
    ) {
    }

    record Result(
            List<Workspace> workspaces
    ) {
    }

}
