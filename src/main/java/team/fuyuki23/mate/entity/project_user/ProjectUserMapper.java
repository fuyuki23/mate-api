package team.fuyuki23.mate.entity.project_user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.fuyuki23.mate.domain.ProjectUser;
import team.fuyuki23.mate.entity.project.ProjectMapper;
import team.fuyuki23.mate.entity.user.UserMapper;
import team.fuyuki23.mate.entity.workspace.WorkspaceMapper;

@Component
@RequiredArgsConstructor
public class ProjectUserMapper {

  private final WorkspaceMapper workspaceMapper;
  private final ProjectMapper projectMapper;
  private final UserMapper userMapper;

  public ProjectUser toDomain(ProjectUserJpaEntity projectUserJpaEntity) {
    return new ProjectUser(
        workspaceMapper.toDomain(projectUserJpaEntity.getWorkspace()),
        projectMapper.toDomain(projectUserJpaEntity.getProject()),
        userMapper.toDomain(projectUserJpaEntity.getUser()),
        projectUserJpaEntity.getRole()
    );
  }

}
