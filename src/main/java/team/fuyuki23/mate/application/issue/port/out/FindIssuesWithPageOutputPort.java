package team.fuyuki23.mate.application.issue.port.out;

import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import team.fuyuki23.mate.domain.Issue;

public interface FindIssuesWithPageOutputPort {

  Page<Issue> findIssuesWithPage(UUID workspaceId, UUID projectId, Pageable pageable);

}
