package team.fuyuki23.mate.common.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import team.fuyuki23.mate.application.common.usecase.ValidateWPUUseCase;
import team.fuyuki23.mate.application.common.usecase.ValidateWPUUseCase.Command;
import team.fuyuki23.mate.common.jwt.JwtService;
import team.fuyuki23.mate.domain.vo.WPU;
import team.fuyuki23.mate.entity.user.UserDetailsEntity;

@Slf4j
@RequiredArgsConstructor
public class WPUAuthenticationProvider implements AuthenticationProvider {

  private final JwtService jwtService;
  private final UserDetailsService userDetailsService;
  private final ValidateWPUUseCase validateWPUUseCase;

  @Override
  public boolean supports(Class<?> authentication) {
    return WPUAuthenticationToken.class.isAssignableFrom(authentication);
  }

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    WPUAuthenticationToken wpuAuthenticationToken = (WPUAuthenticationToken) authentication;
    String email = jwtService.parseAccessToken(wpuAuthenticationToken.getToken());
    UserDetailsEntity user = (UserDetailsEntity) userDetailsService.loadUserByUsername(email);

    // FIXME: project has workspace object, we don't need projects[].workspace object
    WPU wpu = validateWPUUseCase.validateWPU(
        new Command(wpuAuthenticationToken.getWorkspaceId(), user.user())).wpu();

    return new WPUAuthenticationToken(wpu, null, user.getAuthorities());
  }
}
