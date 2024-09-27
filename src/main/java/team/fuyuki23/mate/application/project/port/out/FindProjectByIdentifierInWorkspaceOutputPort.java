package team.fuyuki23.mate.application.project.port.out;

import java.util.Optional;
import java.util.UUID;
import team.fuyuki23.mate.domain.Project;

public interface FindProjectByIdentifierInWorkspaceOutputPort {

  Optional<Project> findProjectByIdentifierInWorkspace(String identifier, UUID workspaceId);

}
