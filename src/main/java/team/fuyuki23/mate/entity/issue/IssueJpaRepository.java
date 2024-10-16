package team.fuyuki23.mate.entity.issue;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueJpaRepository extends JpaRepository<IssueJpaEntity, UUID> {

}
