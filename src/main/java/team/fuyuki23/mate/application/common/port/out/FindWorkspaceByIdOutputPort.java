package team.fuyuki23.mate.application.common.port.out;

import java.util.Optional;
import java.util.UUID;
import team.fuyuki23.mate.domain.Workspace;

public interface FindWorkspaceByIdOutputPort {

  Optional<Workspace> findWorkspaceById(UUID id, UUID userId);

}
