package team.fuyuki23.mate.common.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;
import team.fuyuki23.mate.common.exception.DefaultError;

@Slf4j
@RequiredArgsConstructor
public class AuthenticationExceptionHandlerFilter extends OncePerRequestFilter {

  private final ObjectMapper objectMapper;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    try {
      filterChain.doFilter(request, response);
    } catch (JwtException error) {
      log.error("JWT exception {}", error.getMessage());
      if (!response.isCommitted()) {
        try {
          String responseBody = objectMapper.writeValueAsString(DefaultError.FORBIDDEN.toMap());

          response.setContentType("application/json;charset=UTF-8");
          response.setStatus(DefaultError.UNAUTHORIZED.getStatus());
          response.setCharacterEncoding("UTF-8");
          response.getWriter().write(responseBody);
        } catch (Exception e) {
          // Handle any exceptions that occur during the response writing
          log.error("Error writing response", e);
        }
      }
    }
  }
}
