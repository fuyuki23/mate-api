package team.fuyuki23.mate.adapter.out.persistence.common;

import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.fuyuki23.mate.application.common.port.out.FindProjectBySlugAndIdentifierOutputPort;
import team.fuyuki23.mate.domain.Project;
import team.fuyuki23.mate.entity.project.ProjectMapper;
import team.fuyuki23.mate.entity.project_user.ProjectUserJpaEntity;
import team.fuyuki23.mate.entity.project_user.ProjectUserJpaRepository;

@Repository
@RequiredArgsConstructor
public class ReadProjectPersistenceAdapter implements FindProjectBySlugAndIdentifierOutputPort {

  private final ProjectUserJpaRepository projectUserJpaRepository;
  private final ProjectMapper projectMapper;

  @Override
  public Optional<Project> findProjectBySlugAndIdentifier(UUID workspaceId, String identifier,
      UUID userId) {
    Optional<ProjectUserJpaEntity> projectUser = projectUserJpaRepository.findByWorkspaceIdAndIdentifierAndUserId(
        workspaceId, identifier, userId);

    return projectUser
        .map((it) -> projectMapper.toDomain(it.getProject()));
  }
}
