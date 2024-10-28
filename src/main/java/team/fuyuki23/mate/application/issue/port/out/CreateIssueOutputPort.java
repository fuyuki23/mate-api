package team.fuyuki23.mate.application.issue.port.out;

import team.fuyuki23.mate.domain.Issue;
import team.fuyuki23.mate.domain.Project;
import team.fuyuki23.mate.domain.User;
import team.fuyuki23.mate.domain.Workspace;
import team.fuyuki23.mate.entity.issue_state.IssueStateGroup;

public interface CreateIssueOutputPort {

  Issue createIssue(
      String title,
      String description,
      IssueStateGroup issueStateGroup,
      Workspace workspace,
      Project project,
      User user);

}
