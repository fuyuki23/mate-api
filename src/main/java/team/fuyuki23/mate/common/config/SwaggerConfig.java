package team.fuyuki23.mate.common.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.In;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI openAPI() {
    return new OpenAPI()
        .addSecurityItem(new SecurityRequirement().addList("jwt"))
        .components(
            new Components()
                .addSecuritySchemes("jwt", new SecurityScheme()
                    .type(Type.HTTP)
                    .scheme("bearer")
                    .bearerFormat("JWT")
                    .in(In.HEADER)
                    .name("Authorization")
                    .description("Bearer token")
                )
        );
  }

  @Bean
  public OperationCustomizer customizer() {
    return (operation, handlerMethod) -> operation
        .addParametersItem(
            new Parameter().in("header").name("x-workspace-id").required(false)
                .description("Workspace ID")
        );
  }

}
