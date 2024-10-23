package team.fuyuki23.mate.adapter.out.persistence.issue;

import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.fuyuki23.mate.application.issue.port.out.FindIssueStateByNameOutputPort;
import team.fuyuki23.mate.domain.IssueState;
import team.fuyuki23.mate.entity.issue_state.IssueStateJpaRepository;
import team.fuyuki23.mate.entity.issue_state.IssueStateMapper;

@Repository
@RequiredArgsConstructor
public class ReadIssuePersistenceAdapter implements FindIssueStateByNameOutputPort {

  private final IssueStateJpaRepository issueStateJpaRepository;
  private final IssueStateMapper issueStateMapper;

  @Override
  public Optional<IssueState> findIssueStateByName(String name, UUID workspaceId, UUID projectId) {
    return issueStateJpaRepository
        .findByNameAndWorkspaceIdAndProjectId(name, workspaceId, projectId)
        .map(issueStateMapper::toDomain);
  }
}
