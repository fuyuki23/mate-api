package team.fuyuki23.mate.entity.project;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.fuyuki23.mate.domain.Project;
import team.fuyuki23.mate.entity.user.UserMapper;
import team.fuyuki23.mate.entity.workspace.WorkspaceMapper;

@Component
@RequiredArgsConstructor
public class ProjectMapper {

  private final WorkspaceMapper workspaceMapper;
  private final UserMapper userMapper;

  public Project toDomain(ProjectJpaEntity projectJpaEntity) {
    return new Project(
        projectJpaEntity.getId(),
        projectJpaEntity.getIdentifier(),
        projectJpaEntity.getName(),
        projectJpaEntity.getDescription(),
        projectJpaEntity.getCreatedAt(),
        projectJpaEntity.getUpdatedAt(),
        workspaceMapper.toDomain(projectJpaEntity.getWorkspace()),
        userMapper.toDomain(projectJpaEntity.getLeader())
    );
  }

}
