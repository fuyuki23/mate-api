package team.fuyuki23.mate.application.common.port.out;

import java.util.List;
import java.util.UUID;
import team.fuyuki23.mate.domain.ProjectUser;

public interface FindProjectUsersByWorkspaceIdOutputPort {

  List<ProjectUser> findProjectUsersByWorkspaceId(UUID workspaceId, UUID userId);

}
