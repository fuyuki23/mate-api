package team.fuyuki23.mate.application.issue.port.out;

import team.fuyuki23.mate.domain.Issue;
import team.fuyuki23.mate.domain.Project;
import team.fuyuki23.mate.domain.Workspace;

public interface CreateIssueOutputPort {

  Issue createIssue(
      String title,
      String description,
      Workspace workspace,
      Project project,
      String user);

}
