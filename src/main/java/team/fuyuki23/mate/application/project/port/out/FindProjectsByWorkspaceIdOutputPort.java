package team.fuyuki23.mate.application.project.port.out;

import java.util.List;
import java.util.UUID;
import team.fuyuki23.mate.domain.Project;

public interface FindProjectsByWorkspaceIdOutputPort {

  List<Project> findProjectsByWorkspaceId(UUID workspaceId);

}
