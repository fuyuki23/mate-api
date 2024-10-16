package team.fuyuki23.mate.application.project.port.out;

import java.util.List;
import team.fuyuki23.mate.domain.IssueState;
import team.fuyuki23.mate.domain.Project;

public interface CreateDefaultIssueStateOutputPort {

  List<IssueState> createDefaultIssueState(Project project);

}
