package team.fuyuki23.mate.common.security;

import java.io.Serial;
import java.util.Collection;
import java.util.UUID;
import lombok.Getter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class WPUAuthenticationToken extends AbstractAuthenticationToken {

  @Serial
  private static final long serialVersionUID = 7505864692761194418L;

  private String _token;
  private UUID _workspaceId;

  @Getter
  private Object principal;
  @Getter
  private Object credentials;

  public WPUAuthenticationToken(String token, UUID workspaceId) {
    super(null);
    this._token = token;
    this._workspaceId = workspaceId;
    this.setAuthenticated(false);
  }

  public WPUAuthenticationToken(Object principal, Object credentials,
      Collection<? extends GrantedAuthority> authorities) {
    super(authorities);
    this.principal = principal;
    this.credentials = credentials;
    this.setAuthenticated(true);
  }

  public String getToken() {
    return this._token;
  }

  public UUID getWorkspaceId() {
    return this._workspaceId;
  }

}
