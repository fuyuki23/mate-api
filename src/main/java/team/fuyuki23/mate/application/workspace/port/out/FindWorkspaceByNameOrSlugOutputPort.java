package team.fuyuki23.mate.application.workspace.port.out;

import java.util.Optional;
import team.fuyuki23.mate.domain.Workspace;

public interface FindWorkspaceByNameOrSlugOutputPort {

  Optional<Workspace> findWorkspaceByNameOrSlug(String name, String slug);

}
