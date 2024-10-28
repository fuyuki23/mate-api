package team.fuyuki23.mate.adapter.out.persistence.issue;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.fuyuki23.mate.application.issue.port.out.CreateIssueOutputPort;
import team.fuyuki23.mate.domain.Issue;
import team.fuyuki23.mate.domain.Project;
import team.fuyuki23.mate.domain.User;
import team.fuyuki23.mate.domain.Workspace;
import team.fuyuki23.mate.entity.issue.IssueJpaEntity;
import team.fuyuki23.mate.entity.issue.IssueJpaRepository;
import team.fuyuki23.mate.entity.issue.IssueMapper;
import team.fuyuki23.mate.entity.issue_state.IssueStateGroup;
import team.fuyuki23.mate.entity.issue_state.IssueStateJpaEntity;
import team.fuyuki23.mate.entity.issue_state.IssueStateJpaRepository;

@Repository
@RequiredArgsConstructor
public class WriteIssuePersistenceAdapter implements CreateIssueOutputPort {

  private final IssueJpaRepository issueJpaRepository;
  private final IssueStateJpaRepository issueStateJpaRepository;
  private final IssueMapper issueMapper;

  @Override
  public Issue createIssue(String title,
      String description,
      IssueStateGroup issueStateGroup,
      Workspace workspace,
      Project project,
      User user) {
    IssueStateJpaEntity issueState = issueStateJpaRepository.findByNameAndWorkspaceIdAndProjectId(
        issueStateGroup.name(),
        workspace.id(),
        project.id()
    ).orElseThrow(() -> new RuntimeException("Issue state not found"));

    IssueJpaEntity issueJpaEntity = issueJpaRepository.save(IssueJpaEntity.builder()
        .workspaceId(workspace.id())
        .projectId(project.id())
        .state(issueState)
        .sequenceId(1L)
        .name(title)
        .description(description)
        .build());

    return issueMapper.toDomain(issueJpaEntity);
  }

}
