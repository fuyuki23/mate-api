package team.fuyuki23.mate.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public record Project(UUID id, String identifier, String name, String description,
                      LocalDateTime createdAt, LocalDateTime updatedAt,
                      Workspace workspace, User leader) {

}
