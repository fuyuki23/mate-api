package team.fuyuki23.mate.common.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import team.fuyuki23.mate.application.common.usecase.ValidateWPUUseCase;
import team.fuyuki23.mate.common.config.AccessLoggingFilter;
import team.fuyuki23.mate.common.jwt.JwtService;

@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@Configuration
public class SecurityConfig {

  private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
  private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
  private final JwtAuthenticationProvider jwtAuthenticationProvider;
  private final WPUAuthenticationProvider wpuAuthenticationProvider;

  SecurityConfig(JwtAccessDeniedHandler jwtAccessDeniedHandler,
      JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
      JwtService jwtService,
      UserDetailsService userDetailsService,
      ValidateWPUUseCase validateWPUUseCase) {
    this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
    this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
    this.jwtAuthenticationProvider = new JwtAuthenticationProvider(jwtService, userDetailsService);
    this.wpuAuthenticationProvider = new WPUAuthenticationProvider(jwtService, userDetailsService,
        validateWPUUseCase);
  }

  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(List.of("http://localhost:3000"));
    configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
    configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
    configuration.setAllowCredentials(true);
    configuration.setMaxAge(3600L);

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http,
      AuthenticationManager authenticationManager) throws Exception {
    http.cors(cors -> cors.configurationSource(corsConfigurationSource()))
        .csrf(AbstractHttpConfigurer::disable)
        .httpBasic(AbstractHttpConfigurer::disable)
        .formLogin(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(
            request ->
                request
                    .requestMatchers(HttpMethod.GET, "/api-docs/**", "/swagger-ui/**").permitAll()
                    .requestMatchers(HttpMethod.POST, "/users/login", "/users/register").permitAll()
                    .anyRequest().authenticated()
        )
        .sessionManagement(it -> it.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//        .addFilterAfter(new JwtAuthenticationFilter(authenticationManager), LogoutFilter.class)
        .addFilterBefore(new WPUAuthenticationFilter(authenticationManager),
            LogoutFilter.class)
        .addFilterBefore(new AuthenticationExceptionHandlerFilter(new ObjectMapper()),
            WPUAuthenticationFilter.class)
        .addFilterBefore(new AccessLoggingFilter(), AuthenticationExceptionHandlerFilter.class)
        .exceptionHandling(httpSecurityExceptionHandlingConfigurer ->
            httpSecurityExceptionHandlingConfigurer
                .accessDeniedHandler(jwtAccessDeniedHandler)
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
        );

    return http.build();
  }

  @Bean
  public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
    AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(
        AuthenticationManagerBuilder.class);
    // TODO: JWT 확인 후 접속 할 Workspace 를 확인하는 AuthenticationProvider 도 만들 수 있을듯?
    authenticationManagerBuilder.authenticationProvider(jwtAuthenticationProvider);
    authenticationManagerBuilder.authenticationProvider(wpuAuthenticationProvider);
    return authenticationManagerBuilder.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
