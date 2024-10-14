package team.fuyuki23.mate.application.user.port.in;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import team.fuyuki23.mate.application.user.port.out.FindUserByEmailOutputPort;
import team.fuyuki23.mate.domain.User;
import team.fuyuki23.mate.entity.user.UserDetailsEntity;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsInputPort implements UserDetailsService {

  private final FindUserByEmailOutputPort findUserByEmailOutputPort;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = findUserByEmailOutputPort.findUserByEmail(username);
    if (user == null) {
      throw new UsernameNotFoundException("User not found");
    }

    return new UserDetailsEntity(user);
  }
}
