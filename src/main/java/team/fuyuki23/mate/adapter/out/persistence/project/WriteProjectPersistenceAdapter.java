package team.fuyuki23.mate.adapter.out.persistence.project;

import java.util.EnumSet;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import team.fuyuki23.mate.application.project.port.out.CreateDefaultIssueStateOutputPort;
import team.fuyuki23.mate.application.project.port.out.CreateProjectOutputPort;
import team.fuyuki23.mate.domain.IssueState;
import team.fuyuki23.mate.domain.Project;
import team.fuyuki23.mate.domain.User;
import team.fuyuki23.mate.domain.Workspace;
import team.fuyuki23.mate.entity.issue_state.IssueStateGroup;
import team.fuyuki23.mate.entity.issue_state.IssueStateJpaEntity;
import team.fuyuki23.mate.entity.issue_state.IssueStateJpaRepository;
import team.fuyuki23.mate.entity.issue_state.IssueStateMapper;
import team.fuyuki23.mate.entity.project.ProjectJpaEntity;
import team.fuyuki23.mate.entity.project.ProjectJpaRepository;
import team.fuyuki23.mate.entity.project.ProjectMapper;
import team.fuyuki23.mate.entity.user.UserJpaEntity;
import team.fuyuki23.mate.entity.workspace.WorkspaceJpaEntity;

@Slf4j
@Repository
@RequiredArgsConstructor
public class WriteProjectPersistenceAdapter implements CreateProjectOutputPort,
    CreateDefaultIssueStateOutputPort {

  private final ProjectJpaRepository projectJpaRepository;
  private final ProjectMapper projectMapper;

  private final IssueStateJpaRepository issueStateJpaRepository;
  private final IssueStateMapper issueStateMapper;

  @Override
  public Project createProject(String name, String identifier, String description,
      Workspace workspace, User leader) {
    ProjectJpaEntity projectEntity = ProjectJpaEntity.builder().name(name).identifier(identifier)
        .description(description).workspace(WorkspaceJpaEntity.builder().id(workspace.id()).build())
        .leader(UserJpaEntity.builder().id(leader.id()).build()).build();
    projectEntity = projectJpaRepository.save(projectEntity);

    return projectMapper.toDomain(projectEntity);
  }

  @Override
  public List<IssueState> createDefaultIssueState(Project project) {
    List<IssueStateJpaEntity> entities = EnumSet.allOf(IssueStateGroup.class).stream().map(
        issueStateGroup -> IssueStateJpaEntity.builder().group(issueStateGroup)
            .name(issueStateGroup.getGroup()).color(issueStateGroup.getColor())
            .workspaceId(project.workspace().id()).projectId(project.id()).build()).toList();
    log.info("entities: {}", entities);
    issueStateJpaRepository.saveAll(entities);

    return entities.stream().map(issueStateMapper::toDomain).toList();
  }
}
