package team.fuyuki23.mate.application.user.port.out;

import team.fuyuki23.mate.domain.Account;

public interface FindAccountByEmailOutputPort {

    Account findAccountByEmail(String email);

}
