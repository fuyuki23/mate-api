package team.fuyuki23.mate.adapter.out.persistence.member;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.fuyuki23.mate.application.member.port.out.FindMembersByWorkspaceIdOutputPort;
import team.fuyuki23.mate.application.member.port.out.FindWorkspaceBySlugOutputPort;
import team.fuyuki23.mate.domain.Member;
import team.fuyuki23.mate.domain.Workspace;
import team.fuyuki23.mate.entity.workspace.WorkspaceJpaRepository;
import team.fuyuki23.mate.entity.workspace.WorkspaceMapper;
import team.fuyuki23.mate.entity.workspace_user.WorkspaceUserJpaEntity;
import team.fuyuki23.mate.entity.workspace_user.WorkspaceUserJpaRepository;
import team.fuyuki23.mate.entity.workspace_user.WorkspaceUserMapper;

@Repository
@RequiredArgsConstructor
public class ReadMemberPersistenceAdapter implements FindWorkspaceBySlugOutputPort,
    FindMembersByWorkspaceIdOutputPort {

  private final WorkspaceJpaRepository workspaceJpaRepository;
  private final WorkspaceUserJpaRepository workspaceUserJpaRepository;
  private final WorkspaceMapper workspaceMapper;
  private final WorkspaceUserMapper workspaceUserMapper;

  @Override
  public Optional<Workspace> findWorkspaceBySlug(String slug) {
    return workspaceJpaRepository.findBySlug(slug).map(workspaceMapper::toDomain);
  }

  @Override
  public List<Member> findMembersByWorkspaceId(UUID workspaceId) {
    List<WorkspaceUserJpaEntity> workspaceUsers = workspaceUserJpaRepository.findByWorkspaceId(
        workspaceId);

    return workspaceUsers.stream().map(workspaceUserMapper::toDomain).toList();
  }
}
