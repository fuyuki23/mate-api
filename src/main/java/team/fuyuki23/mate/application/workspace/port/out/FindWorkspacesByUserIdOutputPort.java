package team.fuyuki23.mate.application.workspace.port.out;

import java.util.List;
import java.util.UUID;
import team.fuyuki23.mate.domain.Workspace;

public interface FindWorkspacesByUserIdOutputPort {

  List<Workspace> findWorkspacesByUserId(UUID userId);

}
