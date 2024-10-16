package team.fuyuki23.mate.entity.issue_state;

import org.springframework.stereotype.Component;
import team.fuyuki23.mate.domain.IssueState;

@Component
public class IssueStateMapper {

  public IssueState toDomain(IssueStateJpaEntity issueStateJpaEntity) {
    return new IssueState(
        issueStateJpaEntity.getId(),
        issueStateJpaEntity.getGroup(),
        issueStateJpaEntity.getName(),
        issueStateJpaEntity.getDescription(),
        issueStateJpaEntity.getColor(),
        issueStateJpaEntity.getCreatedAt(),
        issueStateJpaEntity.getUpdatedAt()
    );
  }

}
