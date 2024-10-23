package team.fuyuki23.mate.adapter.out.persistence.common;

import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.fuyuki23.mate.application.common.port.out.FindUserProjectOutputPort;
import team.fuyuki23.mate.application.common.port.out.FindUserWorkspaceBySlugAndUserIdOutputPort;
import team.fuyuki23.mate.domain.Member;
import team.fuyuki23.mate.domain.ProjectUser;
import team.fuyuki23.mate.entity.project.ProjectJpaEntity;
import team.fuyuki23.mate.entity.project.ProjectJpaRepository;
import team.fuyuki23.mate.entity.project_user.ProjectUserId;
import team.fuyuki23.mate.entity.project_user.ProjectUserJpaEntity;
import team.fuyuki23.mate.entity.project_user.ProjectUserJpaRepository;
import team.fuyuki23.mate.entity.project_user.ProjectUserMapper;
import team.fuyuki23.mate.entity.workspace.WorkspaceJpaEntity;
import team.fuyuki23.mate.entity.workspace.WorkspaceJpaRepository;
import team.fuyuki23.mate.entity.workspace.WorkspaceMapper;
import team.fuyuki23.mate.entity.workspace_user.WorkspaceUserId;
import team.fuyuki23.mate.entity.workspace_user.WorkspaceUserJpaEntity;
import team.fuyuki23.mate.entity.workspace_user.WorkspaceUserJpaRepository;
import team.fuyuki23.mate.entity.workspace_user.WorkspaceUserMapper;

@Repository
@RequiredArgsConstructor
public class ReadUserWorkspacePersistenceAdapter implements
    FindUserWorkspaceBySlugAndUserIdOutputPort,
    FindUserProjectOutputPort {

  private final WorkspaceJpaRepository workspaceJpaRepository;
  private final WorkspaceUserJpaRepository workspaceUserJpaRepository;
  private final WorkspaceUserMapper workspaceUserMapper;
  private final WorkspaceMapper workspaceMapper;
  private final ProjectJpaRepository projectJpaRepository;
  private final ProjectUserJpaRepository projectUserJpaRepository;
  private final ProjectUserMapper projectUserMapper;

  @Override
  public Optional<Member> findUserWorkspaceBySlugAndUserId(String slug, UUID userId) {
    Optional<WorkspaceJpaEntity> workspace = workspaceJpaRepository.findBySlug(slug);
    if (workspace.isEmpty()) {
      return Optional.empty();
    }

    Optional<WorkspaceUserJpaEntity> workspaceUser = workspaceUserJpaRepository.findByWorkspaceUserId(
        new WorkspaceUserId(
            workspace.get().getId(),
            userId
        )
    );

    return workspaceUser.map(workspaceUserMapper::toDomain);
  }

  @Override
  public Optional<ProjectUser> findUserProjectByIds(String slug, String identifier, UUID userId) {
    Optional<WorkspaceJpaEntity> maybeWorkspace = workspaceJpaRepository.findBySlug(slug);
    if (maybeWorkspace.isEmpty()) {
      return Optional.empty();
    }
    WorkspaceJpaEntity workspace = maybeWorkspace.get();

    Optional<WorkspaceUserJpaEntity> maybeWorkspaceUser = workspaceUserJpaRepository.findByWorkspaceUserId(
        new WorkspaceUserId(
            maybeWorkspace.get().getId(),
            userId
        )
    );
    if (maybeWorkspaceUser.isEmpty()) {
      return Optional.empty();
    }
    WorkspaceUserJpaEntity workspaceUser = maybeWorkspaceUser.get();

    Optional<ProjectJpaEntity> maybeProject = projectJpaRepository.findByIdentifierAndWorkspaceId(
        identifier, workspace.getId());
    if (maybeProject.isEmpty()) {
      return Optional.empty();
    }
    ProjectJpaEntity project = maybeProject.get();

    Optional<ProjectUserJpaEntity> maybeProjectUser = projectUserJpaRepository.findByProjectUserId(
        new ProjectUserId(workspace.getId(), project.getId(), userId)
    );

    return maybeProjectUser.map(projectUserMapper::toDomain);
  }
}
