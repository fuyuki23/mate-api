package team.fuyuki23.mate.common.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import team.fuyuki23.mate.common.exception.DefaultError;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper;

    public JwtAccessDeniedHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) {
//        Logger.info(request != null ? request.getRequestURL().toString() : "Unknown URL",
//            "access denied",
//            accessDeniedException != null ? accessDeniedException.getMessage() : "No message");

        try {
            String responseBody = objectMapper.writeValueAsString(DefaultError.FORBIDDEN.toMap());

            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(DefaultError.FORBIDDEN.getStatus());
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(responseBody);
        } catch (Exception e) {
            // Handle any exceptions that occur during the response writing
            e.printStackTrace();
        }
    }
}
