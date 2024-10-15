package team.fuyuki23.mate.application.token.port.out;

import java.util.UUID;
import team.fuyuki23.mate.domain.User;

public interface FindUserByIdOutputPort {

  User findUserById(UUID id);

}
