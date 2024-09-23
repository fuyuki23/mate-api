package team.fuyuki23.mate.adapter.out.persistence.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.fuyuki23.mate.application.user.port.out.WriteAccountOutputPort;
import team.fuyuki23.mate.domain.Account;
import team.fuyuki23.mate.entity.user.UserJpaEntity;
import team.fuyuki23.mate.entity.user.UserJpaRepository;
import team.fuyuki23.mate.entity.user.UserMapper;

@Repository
@RequiredArgsConstructor
public class WriteAccountPersistenceAdapter implements WriteAccountOutputPort {

    private final UserJpaRepository userJpaRepository;
    private final UserMapper userMapper;

    @Override
    public Account writeAccount(Account account) {
        UserJpaEntity savedAccount = userJpaRepository.save(
                UserJpaEntity
                        .builder()
                        .email(account.email())
                        .password(account.password())
                        .firstName(account.firstName())
                        .lastName(account.lastName())
                        .build()
        );

        return userMapper.toAccount(savedAccount);
    }

}
