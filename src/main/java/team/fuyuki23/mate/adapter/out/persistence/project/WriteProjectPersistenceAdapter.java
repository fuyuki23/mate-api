package team.fuyuki23.mate.adapter.out.persistence.project;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.fuyuki23.mate.application.project.port.out.CreateProjectOutputPort;
import team.fuyuki23.mate.domain.Project;
import team.fuyuki23.mate.domain.User;
import team.fuyuki23.mate.domain.Workspace;
import team.fuyuki23.mate.entity.project.ProjectJpaEntity;
import team.fuyuki23.mate.entity.project.ProjectJpaRepository;
import team.fuyuki23.mate.entity.project.ProjectMapper;
import team.fuyuki23.mate.entity.user.UserJpaEntity;
import team.fuyuki23.mate.entity.workspace.WorkspaceJpaEntity;

@Repository
@RequiredArgsConstructor
public class WriteProjectPersistenceAdapter implements CreateProjectOutputPort {

  private final ProjectJpaRepository projectJpaRepository;
  private final ProjectMapper projectMapper;

  @Override
  public Project createProject(String name, String identifier, String description,
      Workspace workspace, User leader) {
    ProjectJpaEntity projectEntity = ProjectJpaEntity.builder()
        .name(name)
        .identifier(identifier)
        .description(description)
        .workspace(WorkspaceJpaEntity.builder().id(workspace.id()).build())
        .leader(UserJpaEntity.builder().id(leader.id()).build())
        .build();
    projectEntity = projectJpaRepository.save(
        projectEntity
    );

    return projectMapper.toDomain(projectEntity);
  }
}
