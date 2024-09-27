package team.fuyuki23.mate.application.member.port.out;

import java.util.Optional;
import team.fuyuki23.mate.domain.Workspace;

public interface FindWorkspaceBySlugOutputPort {

  Optional<Workspace> findWorkspaceBySlug(String slug);

}
