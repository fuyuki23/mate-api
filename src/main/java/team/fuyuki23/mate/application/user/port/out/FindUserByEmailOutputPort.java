package team.fuyuki23.mate.application.user.port.out;

import team.fuyuki23.mate.domain.User;

public interface FindUserByEmailOutputPort {

  User findUserByEmail(String email);

}
