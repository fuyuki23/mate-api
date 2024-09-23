package team.fuyuki23.mate.adapter.out.persistence.workspace;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.fuyuki23.mate.application.workspace.port.out.FindWorkspacesByUserIdOutputPort;
import team.fuyuki23.mate.domain.Workspace;
import team.fuyuki23.mate.entity.workspace.WorkspaceMapper;
import team.fuyuki23.mate.entity.workspace_user.WorkspaceUserJpaEntity;
import team.fuyuki23.mate.entity.workspace_user.WorkspaceUserJpaRepository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class FindWorkspacesByUserIdPersistenceAdapter implements FindWorkspacesByUserIdOutputPort {

    private final WorkspaceUserJpaRepository workspaceUserJpaRepository;
    private final WorkspaceMapper workspaceMapper;

    @Override
    public List<Workspace> findWorkspacesByUserId(UUID userId) {
        List<Workspace> workspaces = workspaceUserJpaRepository.findByWorkspaceUserIdUserId(userId)
                .stream()
                .map(WorkspaceUserJpaEntity::getWorkspace)
                .map(workspaceMapper::toDomain)
                .toList();
        return workspaces;
    }
}
