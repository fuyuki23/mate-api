package team.fuyuki23.mate.application.project.port.out;

import java.util.Optional;
import team.fuyuki23.mate.domain.Workspace;

// TODO: pull up to common module or service
public interface FindWorkspaceBySlugOutputPort {

  Optional<Workspace> findWorkspaceBySlug(String slug);

}
