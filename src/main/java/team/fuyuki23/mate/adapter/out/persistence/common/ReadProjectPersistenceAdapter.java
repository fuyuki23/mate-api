package team.fuyuki23.mate.adapter.out.persistence.common;

import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import team.fuyuki23.mate.application.common.port.out.FindProjectBySlugAndIdentifierOutputPort;
import team.fuyuki23.mate.domain.Project;

@Repository
public class ReadProjectPersistenceAdapter implements FindProjectBySlugAndIdentifierOutputPort {

  @Override
  public Optional<Project> findProjectBySlugAndIdentifier(String slug, String identifier,
      UUID userId) {
    return Optional.empty();
  }
}
