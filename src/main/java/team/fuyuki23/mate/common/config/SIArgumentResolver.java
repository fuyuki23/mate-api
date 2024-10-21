package team.fuyuki23.mate.common.config;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import team.fuyuki23.mate.common.annotation.FromPath;
import team.fuyuki23.mate.domain.vo.SI;

@Slf4j
public class SIArgumentResolver implements HandlerMethodArgumentResolver {

  private final Pattern URI_PATTERN = Pattern.compile(
      "^\\/workspaces\\/([a-z0-9_-]+)\\/projects\\/([A-Z0-9_-]+).*");

  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    // SI 타입의 파라미터일 때만 처리
    return SI.class.isAssignableFrom(parameter.getParameterType())
        && parameter.hasParameterAnnotation(FromPath.class);
  }

  @Override
  public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
      NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
    HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
    String requestURI = request.getRequestURI();

    return validateURI(requestURI).orElseThrow(() -> new BadRequestException("Invalid URI"));
  }

  private Optional<SI> validateURI(String uri) {
    Matcher matcher = URI_PATTERN.matcher(uri);
    System.out.println("^\\/workspaces\\/([a-z0-9_-]+)\\/projects\\/([A-Z0-9_-]+).*");
    System.out.println(uri);
    if (matcher.matches()) {
      return Optional.of(new SI(matcher.group(1), matcher.group(2)));
    } else {
      return Optional.empty();
    }
  }
}
