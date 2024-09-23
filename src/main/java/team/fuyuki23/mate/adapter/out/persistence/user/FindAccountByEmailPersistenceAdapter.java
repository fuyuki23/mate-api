package team.fuyuki23.mate.adapter.out.persistence.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.fuyuki23.mate.application.user.port.out.FindAccountByEmailOutputPort;
import team.fuyuki23.mate.domain.Account;
import team.fuyuki23.mate.entity.user.UserJpaRepository;
import team.fuyuki23.mate.entity.user.UserMapper;

@Repository
@RequiredArgsConstructor
public class FindAccountByEmailPersistenceAdapter implements FindAccountByEmailOutputPort {

    private final UserJpaRepository userJpaRepository;
    private final UserMapper userMapper;

    @Override
    public Account findAccountByEmail(String email) {
        return userJpaRepository.findByEmail(email)
                .map(userMapper::toAccount)
                .orElse(null);
    }
}
