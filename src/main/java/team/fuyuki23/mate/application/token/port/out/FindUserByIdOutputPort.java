package team.fuyuki23.mate.application.token.port.out;

import team.fuyuki23.mate.domain.User;

import java.util.UUID;

public interface FindUserByIdOutputPort {

    User findUserById(UUID id);

}
