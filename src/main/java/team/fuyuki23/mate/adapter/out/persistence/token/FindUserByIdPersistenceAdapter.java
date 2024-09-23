package team.fuyuki23.mate.adapter.out.persistence.token;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.fuyuki23.mate.application.token.port.out.FindUserByIdOutputPort;
import team.fuyuki23.mate.domain.User;
import team.fuyuki23.mate.entity.user.UserJpaRepository;
import team.fuyuki23.mate.entity.user.UserMapper;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class FindUserByIdPersistenceAdapter implements FindUserByIdOutputPort {
    private final UserJpaRepository userJpaRepository;
    private final UserMapper userMapper;

    @Override
    public User findUserById(UUID id) {
        return userJpaRepository.findById(id)
                .map(userMapper::toDomain).orElse(null);
    }
}
