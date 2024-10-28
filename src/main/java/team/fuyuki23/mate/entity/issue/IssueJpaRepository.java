package team.fuyuki23.mate.entity.issue;

import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueJpaRepository extends JpaRepository<IssueJpaEntity, UUID> {

  Page<IssueJpaEntity> findAllByWorkspaceIdAndProjectId(UUID workspaceId, UUID projectId,
      Pageable pageable);

}
