package team.fuyuki23.mate.common.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import team.fuyuki23.mate.domain.vo.AccessLog;

@Slf4j
public class AccessLoggingFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    ContentCachingRequestWrapper req = new ContentCachingRequestWrapper(request);
    ContentCachingResponseWrapper res = new ContentCachingResponseWrapper(response);
    LocalDateTime now = LocalDateTime.now();

    try {
      filterChain.doFilter(req, response);
    } finally {
      LocalDateTime endAt = LocalDateTime.now();

      long elapsedTime = now.until(endAt, ChronoUnit.MILLIS);
      AccessLog accessLog = new AccessLog(req, res, elapsedTime);

      log.info(accessLog.format());
    }
  }


}
