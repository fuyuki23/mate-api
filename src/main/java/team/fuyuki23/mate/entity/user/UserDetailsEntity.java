package team.fuyuki23.mate.entity.user;

import java.io.Serial;
import java.util.Collection;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import team.fuyuki23.mate.domain.User;

@RequiredArgsConstructor
public class UserDetailsEntity implements UserDetails {

  @Serial
  private static final long serialVersionUID = 1865299883012833840L;

  private final User _user;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of();
  }

  @Override
  public String getPassword() {
    return "";
  }

  @Override
  public String getUsername() {
    return _user.email();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  public User user() {
    return _user;
  }
}
