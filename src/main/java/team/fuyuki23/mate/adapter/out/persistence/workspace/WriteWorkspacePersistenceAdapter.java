package team.fuyuki23.mate.adapter.out.persistence.workspace;

import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.fuyuki23.mate.application.workspace.port.out.CreateWorkspaceOutputPort;
import team.fuyuki23.mate.domain.User;
import team.fuyuki23.mate.domain.Workspace;
import team.fuyuki23.mate.entity.user.UserJpaEntity;
import team.fuyuki23.mate.entity.workspace.WorkspaceJpaEntity;
import team.fuyuki23.mate.entity.workspace.WorkspaceJpaRepository;
import team.fuyuki23.mate.entity.workspace.WorkspaceMapper;
import team.fuyuki23.mate.entity.workspace_user.WorkspaceUserId;
import team.fuyuki23.mate.entity.workspace_user.WorkspaceUserJpaEntity;
import team.fuyuki23.mate.entity.workspace_user.WorkspaceUserJpaRepository;

@Repository
@RequiredArgsConstructor
public class WriteWorkspacePersistenceAdapter implements CreateWorkspaceOutputPort {

  private final WorkspaceUserJpaRepository workspaceUserJpaRepository;
  private final WorkspaceJpaRepository workspaceJpaRepository;
  private final WorkspaceMapper workspaceMapper;

  @Override
  @Transactional
  public Workspace createWorkspace(String name, String slug, int size, User requester) {
    WorkspaceJpaEntity workspaceEntity = workspaceJpaRepository.save(
        WorkspaceJpaEntity.builder().name(name).slug(slug).size(size).build());

    workspaceUserJpaRepository.save(
        WorkspaceUserJpaEntity.builder()
            .workspaceUserId(
                new WorkspaceUserId(workspaceEntity.getId(), requester.id())
            )
            // FIXME: This should be a role enum, 0 ANONYMOUS, 10 MEMBER, 50 ADMIN, 100 OWNER
            .role(10)
            .createdAt(LocalDateTime.now())
            .workspace(workspaceEntity)
            .user(UserJpaEntity.builder().id(requester.id()).build())
            .build()
    );

    return workspaceMapper.toDomain(workspaceEntity);
  }
}
