package team.fuyuki23.mate.common.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import team.fuyuki23.mate.common.jwt.JwtService;
import team.fuyuki23.mate.entity.user.UserDetailsEntity;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {

  private final JwtService jwtService;
  private final UserDetailsService userDetailsService;

  @Override
  public boolean supports(Class<?> authentication) {
    return JwtAuthenticationToken.class.isAssignableFrom(authentication);
  }

  @Override
  public Authentication authenticate(Authentication authentication) {
    JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) authentication;
    String email = jwtService.parseAccessToken(jwtAuthenticationToken.getToken());
    UserDetailsEntity user = (UserDetailsEntity) userDetailsService.loadUserByUsername(email);

    return new JwtAuthenticationToken(user.user(), null, user.getAuthorities());
  }

}
