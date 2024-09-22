package team.fuyuki23.mate.adapter.out.persistence.user;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team.fuyuki23.mate.application.user.port.out.FindUserByEmailOutputPort;
import team.fuyuki23.mate.domain.User;
import team.fuyuki23.mate.entity.user.UserJpaEntity;
import team.fuyuki23.mate.entity.user.UserJpaRepository;
import team.fuyuki23.mate.entity.user.UserMapper;

@Repository
@RequiredArgsConstructor
public class FindUserByEmailPersistenceAdapter implements FindUserByEmailOutputPort {

  private final UserJpaRepository userJpaRepository;
  private final UserMapper userMapper;

  @Override
  public User findUserByEmail(String email) {
    Optional<UserJpaEntity> userEntity = userJpaRepository.findByEmail(email);

    // FIXME: throw exception if userEntity is empty not return null
    return userEntity.map(userMapper::toDomain).orElse(null);

  }

}
