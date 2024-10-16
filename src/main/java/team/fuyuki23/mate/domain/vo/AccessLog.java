package team.fuyuki23.mate.domain.vo;

import lombok.Getter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

@Getter
public class AccessLog {

  private final String protocol;
  private final String url;
  private final String method;
  private final String queryString;
  private final Long requestSize;
  private final Long elapsedTime;
  private final Integer statusCode;
  private final Integer responseSize;

  public AccessLog(ContentCachingRequestWrapper request, ContentCachingResponseWrapper response,
      Long elapsedTime) {
    this.protocol = request.getProtocol();
    this.url = request.getRequestURI();
    this.method = request.getMethod();
    this.queryString = request.getQueryString();
    this.requestSize = request.getContentLengthLong();
    this.elapsedTime = elapsedTime;
    this.statusCode = response.getStatus();
    this.responseSize = response.getContentSize();
  }

  private String getQueryString() {
    return queryString == null || queryString.isEmpty() ? "" : "?" + queryString;
  }

  public String format() {
    // GET /api?param1=value HTTP/1.1 200 - 0 0 1ms
    return String.format("%s %s%s %s %d - %d %d %dms",
        method, url, this.getQueryString(), protocol, statusCode, requestSize, responseSize,
        elapsedTime);
  }

}
