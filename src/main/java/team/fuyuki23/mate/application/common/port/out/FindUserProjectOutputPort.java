package team.fuyuki23.mate.application.common.port.out;

import java.util.Optional;
import java.util.UUID;
import team.fuyuki23.mate.domain.ProjectUser;

public interface FindUserProjectOutputPort {

  Optional<ProjectUser> findUserProjectByIds(String slug, String identifier, UUID userId);

}
