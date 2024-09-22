//package team.fuyuki23.mate.common.security;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.core.annotation.Order;
//import org.springframework.http.HttpHeaders;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Order(0)
//@Component
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//
//    private final UserMapper userMapper;
//    private final TokenService tokenService;
//    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
//
//    public JwtAuthenticationFilter(UserMapper userMapper, TokenService tokenService) {
//        this.userMapper = userMapper;
//        this.tokenService = tokenService;
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) {
//        String token = req.getHeader(HttpHeaders.AUTHORIZATION);
//        if (token != null && token.startsWith("Bearer ")) {
//            token = token.substring(7); // Remove "Bearer " prefix
//
//            try {
//                String userId = tokenService.parseAccessToken(token, true);
//                if (userId == null) {
//                    throw new ApiException(DefaultError.UNAUTHORIZED);
//                }
//
//                var user = transaction(() -> UserDao.findById(userId)
//                    .orElseThrow(() -> new UsernameNotFoundException("User not found")));
//
//                List<SimpleGrantedAuthority> authorities = user.getAuthorities().stream()
//                    .map(SimpleGrantedAuthority::new)
//                    .collect(Collectors.toList());
//
//                var authentication = new UsernamePasswordAuthenticationToken(
//                    userMapper.fromDaoToDomain(user),
//                    null,
//                    authorities
//                );
//
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            } catch (UsernameNotFoundException e) {
//                logger.warn("Authentication failed: {}", e.getMessage());
//                res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
//                return; // Stop further processing
//            } catch (Exception e) {
//                logger.error("Error during authentication: {}", e.getMessage(), e);
//                res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal Server Error");
//                return; // Stop further processing
//            }
//        }
//
//        chain.doFilter(req, res);
//    }
//}
