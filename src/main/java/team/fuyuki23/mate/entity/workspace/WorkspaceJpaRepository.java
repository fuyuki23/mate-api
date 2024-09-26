package team.fuyuki23.mate.entity.workspace;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkspaceJpaRepository extends JpaRepository<WorkspaceJpaEntity, UUID> {

    Optional<WorkspaceJpaEntity> findByNameOrSlug(String name, String slug);

}
