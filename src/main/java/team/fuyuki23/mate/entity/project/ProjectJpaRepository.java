package team.fuyuki23.mate.entity.project;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectJpaRepository extends JpaRepository<ProjectJpaEntity, UUID> {

  List<ProjectJpaEntity> findByWorkspaceId(UUID workspaceId);

  Optional<ProjectJpaEntity> findByIdentifierAndWorkspaceId(String identifier, UUID workspaceId);

}
