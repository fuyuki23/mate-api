package team.fuyuki23.mate.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public record Issue(
    UUID id,
    Long sequenceId,
    String name,
    String description,
    IssueState state,
    Issue parent,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    Project project
) {

}
