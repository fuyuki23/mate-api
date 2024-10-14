package team.fuyuki23.mate.common.security;

import java.io.Serial;
import java.util.Collection;
import lombok.Getter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {

  @Serial
  private static final long serialVersionUID = 2505764675492310357L;
  
  private String _token;
  @Getter
  private Object principal;
  @Getter
  private Object credentials;

  public JwtAuthenticationToken(String token) {
    super(null);
    this._token = token;
    this.setAuthenticated(false);
  }

  public JwtAuthenticationToken(Object principal, Object credentials,
      Collection<? extends GrantedAuthority> authorities) {
    super(authorities);
    this.principal = principal;
    this.credentials = credentials;
    super.setAuthenticated(true);
  }

  public String getToken() {
    return this._token;
  }

}
