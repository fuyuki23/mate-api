package team.fuyuki23.mate.adapter.out.persistence.issue;

import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import team.fuyuki23.mate.application.issue.port.out.FindIssueStateByNameOutputPort;
import team.fuyuki23.mate.application.issue.port.out.FindIssuesWithPageOutputPort;
import team.fuyuki23.mate.domain.Issue;
import team.fuyuki23.mate.domain.IssueState;
import team.fuyuki23.mate.entity.issue.IssueJpaRepository;
import team.fuyuki23.mate.entity.issue.IssueMapper;
import team.fuyuki23.mate.entity.issue_state.IssueStateJpaRepository;
import team.fuyuki23.mate.entity.issue_state.IssueStateMapper;

@Repository
@RequiredArgsConstructor
public class ReadIssuePersistenceAdapter implements FindIssueStateByNameOutputPort,
    FindIssuesWithPageOutputPort {

  private final IssueJpaRepository issueJpaRepository;
  private final IssueMapper issueMapper;
  private final IssueStateJpaRepository issueStateJpaRepository;
  private final IssueStateMapper issueStateMapper;

  @Override
  public Optional<IssueState> findIssueStateByName(String name, UUID workspaceId, UUID projectId) {
    return issueStateJpaRepository
        .findByNameAndWorkspaceIdAndProjectId(name, workspaceId, projectId)
        .map(issueStateMapper::toDomain);
  }

  @Override
  public Page<Issue> findIssuesWithPage(UUID workspaceId, UUID projectId, Pageable pageable) {
    return issueJpaRepository.findAllByWorkspaceIdAndProjectId(workspaceId, projectId, pageable)
        .map(issueMapper::toDomain);
  }
}
