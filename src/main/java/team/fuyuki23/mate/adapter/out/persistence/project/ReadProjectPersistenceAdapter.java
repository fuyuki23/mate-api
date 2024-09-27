package team.fuyuki23.mate.adapter.out.persistence.project;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.fuyuki23.mate.application.project.port.out.FindProjectByIdentifierInWorkspaceOutputPort;
import team.fuyuki23.mate.application.project.port.out.FindProjectsByWorkspaceIdOutputPort;
import team.fuyuki23.mate.application.project.port.out.FindWorkspaceBySlugOutputPort;
import team.fuyuki23.mate.domain.Project;
import team.fuyuki23.mate.domain.Workspace;
import team.fuyuki23.mate.entity.project.ProjectJpaRepository;
import team.fuyuki23.mate.entity.project.ProjectMapper;
import team.fuyuki23.mate.entity.workspace.WorkspaceJpaRepository;
import team.fuyuki23.mate.entity.workspace.WorkspaceMapper;

@Repository
@RequiredArgsConstructor
public class ReadProjectPersistenceAdapter implements FindProjectsByWorkspaceIdOutputPort,
    FindWorkspaceBySlugOutputPort,
    FindProjectByIdentifierInWorkspaceOutputPort {

  private final WorkspaceJpaRepository workspaceJpaRepository;
  private final ProjectJpaRepository projectJpaRepository;
  private final ProjectMapper projectMapper;
  private final WorkspaceMapper workspaceMapper;

  @Override
  public Optional<Workspace> findWorkspaceBySlug(String slug) {
    return workspaceJpaRepository.findBySlug(slug)
        .map(workspaceMapper::toDomain);
  }

  @Override
  public List<Project> findProjectsByWorkspaceId(UUID workspaceId) {
    return projectJpaRepository.findByWorkspaceId(workspaceId)
        .stream()
        .map(projectMapper::toDomain)
        .toList();
  }

  @Override
  public Optional<Project> findProjectByIdentifierInWorkspace(String identifier, UUID workspaceId) {
    return projectJpaRepository.findByIdentifierAndWorkspaceId(identifier, workspaceId)
        .map(projectMapper::toDomain);
  }
}
