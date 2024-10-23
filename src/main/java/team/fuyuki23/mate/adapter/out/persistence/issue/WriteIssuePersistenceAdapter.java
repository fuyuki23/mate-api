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

@Repository
@RequiredArgsConstructor
public class WriteIssuePersistenceAdapter implements CreateIssueOutputPort {

  private final IssueJpaRepository issueJpaRepository;
  private final IssueMapper issueMapper;

  @Override
  public Issue createIssue(String title,
      String description,
      Workspace workspace,
      Project project,
      User user) {
    IssueJpaEntity issueJpaEntity = issueJpaRepository.save(IssueJpaEntity.builder()
        .workspaceId(workspace.id())
        .projectId(project.id())
        .sequenceId(1L)
        .name(title)
        .description(description)
        .build());

    return issueMapper.toDomain(issueJpaEntity);
  }

}
