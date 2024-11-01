package team.fuyuki23.mate.common.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@RequiredArgsConstructor
public class WPUAuthenticationFilter extends OncePerRequestFilter {

  private final AuthenticationManager authenticationManager;
  private final Pattern project = Pattern.compile(
      "^\\/workspaces\\/([a-z0-9_-]+)\\/projects\\/([A-Z0-9_-]+).*");
  private final Pattern workspace = Pattern.compile(
      "^\\/workspaces\\/([a-zA-Z0-9_-]+).*");

  private final String WORKSPACE_HEADER_NAME = "x-workspace-id";

  @Override
  protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res,
      FilterChain chain) throws ServletException, IOException {
    log.debug("WPU authentication filter");
    if (SecurityContextHolder.getContext().getAuthentication() == null) {
      WPUAuthenticationToken authenticationToken = createWPUAuthenticationToken(req);
      if (authenticationToken != null) {
        log.debug("WPU authentication token found");
        try {
          Authentication authentication = authenticationManager.authenticate(authenticationToken);
          SecurityContextHolder.getContext().setAuthentication(authentication);
          log.debug("WPU authentication successful");
        } catch (AuthenticationException e) {
          log.error("Error authenticating token", e);
          SecurityContextHolder.clearContext();
        }
      }
    } else {
      log.debug("[WPU] authentication token already exists");
    }

    chain.doFilter(req, res);
  }

  private WPUAuthenticationToken createWPUAuthenticationToken(HttpServletRequest req) {
    String token = req.getHeader(HttpHeaders.AUTHORIZATION);
    if (token == null || !token.startsWith("Bearer ")) {
      return null;
    }
    token = token.substring(7);
//    String uri = req.getServletPath();
    try {
      String selectedWorkspaceId = req.getHeader(this.WORKSPACE_HEADER_NAME);
      if (selectedWorkspaceId != null || selectedWorkspaceId.isBlank()) {
        UUID workspaceId = UUID.fromString(req.getHeader(this.WORKSPACE_HEADER_NAME));
        return new WPUAuthenticationToken(token, workspaceId);
      } else {
        String uri = req.getServletPath();
        Matcher workspaceMatcher = this.workspace.matcher(uri);
        if (workspaceMatcher.matches()) {
          UUID workspaceId = UUID.fromString(workspaceMatcher.group(1));
          return new WPUAuthenticationToken(token, workspaceId);
        }
      }
    } catch (IllegalArgumentException | NullPointerException e) {
      return new WPUAuthenticationToken(token, null);
    }

    return null;
//    Matcher projectMatcher = this.project.matcher(uri);
//    Matcher workspaceMatcher = this.workspace.matcher(uri);
//    if (projectMatcher.matches()) {
//      String slug = projectMatcher.group(1);
//      String identifier = projectMatcher.group(2);
//      return new WPUAuthenticationToken(token, slug, Optional.of(identifier));
//    } else if (workspaceMatcher.matches()) {
//      String slug = workspaceMatcher.group(1);
//      return new WPUAuthenticationToken(token, slug, Optional.empty());
//    }
//
//    return null;
  }
}
