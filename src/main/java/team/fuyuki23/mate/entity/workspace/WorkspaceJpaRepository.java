package team.fuyuki23.mate.entity.workspace;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WorkspaceJpaRepository extends JpaRepository<WorkspaceJpaEntity, UUID> {
}
