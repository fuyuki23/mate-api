package team.fuyuki23.mate.entity.issue_state;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueStateJpaRepository extends JpaRepository<IssueStateJpaEntity, UUID> {

}
