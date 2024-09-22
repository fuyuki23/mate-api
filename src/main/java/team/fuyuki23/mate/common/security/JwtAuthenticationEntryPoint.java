package team.fuyuki23.mate.common.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import team.fuyuki23.mate.common.exception.DefaultError;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;
//    private final Logger log = Logger.getLogger(JwtAuthenticationEntryPoint.class);

    public JwtAuthenticationEntryPoint(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {
//        log.info(String.format("%s %s %s",
//            request != null ? request.getRequestURL().toString() : "Unknown URL",
//            "access denied",
//            authException != null ? authException.getMessage() : "No message"));

        try {
            String responseBody = objectMapper.writeValueAsString(DefaultError.UNAUTHORIZED.toMap());

            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(DefaultError.UNAUTHORIZED.getStatus());
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(responseBody);
        } catch (Exception e) {
            // Handle any exceptions that occur during the response writing
            e.printStackTrace();
        }
    }
}
