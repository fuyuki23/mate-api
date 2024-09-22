package team.fuyuki23.mate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import team.fuyuki23.mate.common.config.JwtConfig;

@EnableJpaAuditing
@EnableConfigurationProperties(JwtConfig.class)
@SpringBootApplication
public class MateApplication {

  public static void main(String[] args) {
    SpringApplication.run(MateApplication.class, args);
  }

}
