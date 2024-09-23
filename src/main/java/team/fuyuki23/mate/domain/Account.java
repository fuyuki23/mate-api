package team.fuyuki23.mate.domain;

import java.util.UUID;

public record Account(UUID id, String email, String password, String firstName, String lastName) {
}
