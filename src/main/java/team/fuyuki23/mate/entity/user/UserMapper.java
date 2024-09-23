package team.fuyuki23.mate.entity.user;

import org.springframework.stereotype.Component;
import team.fuyuki23.mate.domain.Account;
import team.fuyuki23.mate.domain.User;

@Component
public class UserMapper {

    public User toDomain(UserJpaEntity userEntity) {
        return new User(
                userEntity.getId(),
                userEntity.getEmail(),
                userEntity.getFirstName(),
                userEntity.getLastName()
        );
    }

    public Account toAccount(UserJpaEntity userEntity) {
        return new Account(
                userEntity.getId(),
                userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.getFirstName(),
                userEntity.getLastName()
        );
    }

    public User toUser(Account account) {
        return new User(
                account.id(),
                account.email(),
                account.firstName(),
                account.lastName()
        );
    }

}
