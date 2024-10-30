package team.fuyuki23.mate.application.common.port.out;

import java.util.Optional;
import java.util.UUID;
import team.fuyuki23.mate.domain.Project;

public interface FindProjectBySlugAndIdentifierOutputPort {

  Optional<Project> findProjectBySlugAndIdentifier(UUID workspaceId, String identifier,
      UUID userId);

}
