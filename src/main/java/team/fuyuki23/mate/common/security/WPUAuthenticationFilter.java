package team.fuyuki23.mate.common.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@RequiredArgsConstructor
public class WPUAuthenticationFilter extends OncePerRequestFilter {

  private final AuthenticationManager authenticationManager;
  private final Pattern pattern = Pattern.compile(
      "^\\/workspaces\\/([a-z0-9_-]+)\\/projects\\/([A-Z0-9_-]+).*");

  @Override
  protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res,
      FilterChain chain) throws ServletException, IOException {
    log.debug("WPU authentication filter");
    WPUAuthenticationToken authenticationToken = createWPUAuthenticationToken(req);
    if (authenticationToken != null) {
      log.debug("WPU authentication token found");
      try {
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.debug("WPU authentication successful");
      } catch (Exception e) {
        log.error("Error authenticating token", e);
        SecurityContextHolder.clearContext();
      }
    }

    chain.doFilter(req, res);
  }

  private WPUAuthenticationToken createWPUAuthenticationToken(HttpServletRequest req) {
    String token = req.getHeader(HttpHeaders.AUTHORIZATION);
    if (token == null || !token.startsWith("Bearer ")) {
      return null;
    }
    String uri = req.getServletPath();
    Matcher matcher = this.pattern.matcher(uri);
    if (matcher.matches()) {
      String slug = matcher.group(1);
      String identifier = matcher.group(2);
      return new WPUAuthenticationToken(token, slug, identifier);
    }

    return null;
  }
}
