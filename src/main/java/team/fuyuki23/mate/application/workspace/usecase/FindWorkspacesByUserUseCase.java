package team.fuyuki23.mate.application.workspace.usecase;

import java.util.List;
import team.fuyuki23.mate.domain.User;
import team.fuyuki23.mate.domain.Workspace;

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
