package team.fuyuki23.mate.adapter.out.persistence.common;

import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.fuyuki23.mate.application.common.port.out.FindUserWorkspaceBySlugAndUserIdOutputPort;
import team.fuyuki23.mate.domain.Member;
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
    FindUserWorkspaceBySlugAndUserIdOutputPort {

  private final WorkspaceJpaRepository workspaceJpaRepository;
  private final WorkspaceUserJpaRepository workspaceUserJpaRepository;
  private final WorkspaceUserMapper workspaceUserMapper;
  private final WorkspaceMapper workspaceMapper;

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

}
