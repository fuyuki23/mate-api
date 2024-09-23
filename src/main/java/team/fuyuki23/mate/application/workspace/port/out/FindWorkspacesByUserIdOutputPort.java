package team.fuyuki23.mate.application.workspace.port.out;

import team.fuyuki23.mate.domain.Workspace;

import java.util.List;
import java.util.UUID;

public interface FindWorkspacesByUserIdOutputPort {

    List<Workspace> findWorkspacesByUserId(UUID userId);

}
