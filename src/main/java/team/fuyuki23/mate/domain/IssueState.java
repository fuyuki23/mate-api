package team.fuyuki23.mate.domain;

import java.time.LocalDateTime;
import java.util.UUID;
import team.fuyuki23.mate.entity.issue_state.IssueStateGroup;

public record IssueState(
    UUID id,
    IssueStateGroup group,
    String name,
    String description,
    String color,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {

}
