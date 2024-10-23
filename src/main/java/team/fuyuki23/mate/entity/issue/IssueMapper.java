package team.fuyuki23.mate.entity.issue;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.fuyuki23.mate.domain.Issue;
import team.fuyuki23.mate.entity.issue_state.IssueStateMapper;
import team.fuyuki23.mate.entity.project.ProjectMapper;

@Component
@RequiredArgsConstructor
public class IssueMapper {

  private final ProjectMapper projectMapper;
  private final IssueStateMapper issueStateMapper;

  public Issue toDomain(IssueJpaEntity issueJpaEntity) {
    if (issueJpaEntity == null) {
      return null;
    }

    return new Issue(
        issueJpaEntity.getId(),
        issueJpaEntity.getSequenceId(),
        issueJpaEntity.getName(),
        issueJpaEntity.getDescription(),
        issueStateMapper.toDomain(issueJpaEntity.getState()),
        this.toDomain(issueJpaEntity.getParent()),
        issueJpaEntity.getCreatedAt(),
        issueJpaEntity.getUpdatedAt(),
        null
//        projectMapper.toDomain(issueJpaEntity.getProject())
    );
  }

}
