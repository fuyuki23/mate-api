package team.fuyuki23.mate.common.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import team.fuyuki23.mate.application.token.usecase.ValidateAuthnUseCase;
import team.fuyuki23.mate.common.exception.ApiException;
import team.fuyuki23.mate.common.exception.DefaultError;
import team.fuyuki23.mate.common.jwt.JwtService;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Order(0)
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final ValidateAuthnUseCase validateAuthnUseCase;
    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String token = req.getHeader(HttpHeaders.AUTHORIZATION);
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7); // Remove "Bearer " prefix

            try {
                UUID userId = jwtService.parseAccessToken(token, true);
                if (userId == null) {
                    throw new ApiException(DefaultError.UNAUTHORIZED);
                }

                ValidateAuthnUseCase.Result result = validateAuthnUseCase.validateAuthn(new ValidateAuthnUseCase.Command(userId));
                if (!result.isValid()) {
                    throw new UsernameNotFoundException("User not found");
                }

                List<SimpleGrantedAuthority> authorities = Stream.of("ROLE_USER")
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

                var authentication = new UsernamePasswordAuthenticationToken(
                        result.user(),
                        null,
                        authorities
                );

                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (UsernameNotFoundException e) {
                log.warn("Authentication failed: {}", e.getMessage());
                res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
                return; // Stop further processing
            } catch (ApiException e) {
                log.warn("Authentication failed: {}", e.getMessage());
                res.sendError(e.getStatus(), e.getMessage());
                return; // Stop further processing
            } catch (Exception e) {
                log.error("Error during authentication: {}", e.getMessage(), e);
                res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal Server Error");
                return; // Stop further processing
            }
        }

        chain.doFilter(req, res);
    }
}
