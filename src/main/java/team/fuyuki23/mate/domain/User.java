package team.fuyuki23.mate.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.UUID;

public record User(UUID id, String email, @JsonIgnore String password, String firstName,
                   String lastName) {

}
