package team.fuyuki23.mate.application.workspace.port.out;

import java.util.Optional;
import java.util.UUID;
import team.fuyuki23.mate.domain.Workspace;

public interface FindWorkspaceBySlugAndUserIdOutputPort {

  Optional<Workspace> findWorkspaceBySlugAndUserId(String slug, UUID userId);

}
