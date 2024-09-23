package team.fuyuki23.mate.domain;

import java.util.UUID;

public record Workspace(UUID id, String name, String slug, Integer size) {
}
