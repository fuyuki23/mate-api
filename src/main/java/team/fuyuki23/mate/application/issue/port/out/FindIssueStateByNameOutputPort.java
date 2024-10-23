package team.fuyuki23.mate.application.issue.port.out;

import java.util.Optional;
import java.util.UUID;
import team.fuyuki23.mate.domain.IssueState;

public interface FindIssueStateByNameOutputPort {

  Optional<IssueState> findIssueStateByName(String name, UUID workspaceId);

}
