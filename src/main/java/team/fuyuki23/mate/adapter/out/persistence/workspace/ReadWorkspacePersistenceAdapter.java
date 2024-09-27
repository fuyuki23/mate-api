package team.fuyuki23.mate.adapter.out.persistence.workspace;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.fuyuki23.mate.application.workspace.port.out.FindWorkspaceByNameOrSlugOutputPort;
import team.fuyuki23.mate.application.workspace.port.out.FindWorkspaceBySlugAndUserIdOutputPort;
import team.fuyuki23.mate.application.workspace.port.out.FindWorkspacesByUserIdOutputPort;
import team.fuyuki23.mate.domain.Workspace;
import team.fuyuki23.mate.entity.workspace.WorkspaceJpaRepository;
import team.fuyuki23.mate.entity.workspace.WorkspaceMapper;
import team.fuyuki23.mate.entity.workspace_user.WorkspaceUserJpaEntity;
import team.fuyuki23.mate.entity.workspace_user.WorkspaceUserJpaRepository;

@Repository
@RequiredArgsConstructor
public class ReadWorkspacePersistenceAdapter implements FindWorkspacesByUserIdOutputPort,
    FindWorkspaceByNameOrSlugOutputPort,
    FindWorkspaceBySlugAndUserIdOutputPort {

    private final WorkspaceUserJpaRepository workspaceUserJpaRepository;
    private final WorkspaceJpaRepository workspaceJpaRepository;
    private final WorkspaceMapper workspaceMapper;

    @Override
    public List<Workspace> findWorkspacesByUserId(UUID userId) {
        return workspaceUserJpaRepository.findByUserId(userId)
                .stream()
                .map(WorkspaceUserJpaEntity::getWorkspace)
                .map(workspaceMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Workspace> findWorkspaceByNameOrSlug(String name, String slug) {
        return workspaceJpaRepository.findByNameOrSlug(name, slug)
            .map(workspaceMapper::toDomain);
    }

    @Override
    public Optional<Workspace> findWorkspaceBySlugAndUserId(String slug, UUID userId) {
        return workspaceUserJpaRepository.findBySlugAndUserId(slug, userId)
            .map(WorkspaceUserJpaEntity::getWorkspace)
            .map(workspaceMapper::toDomain);
    }
}
