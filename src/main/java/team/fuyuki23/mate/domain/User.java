package team.fuyuki23.mate.domain;

import java.util.UUID;

public record User(UUID id, String email, String firstName,
                   String lastName) {

}
