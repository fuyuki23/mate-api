package team.fuyuki23.mate.entity.user;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserJpaRepositoryTest {

  @Container
  public static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0.39")
      .withDatabaseName("mate")
      .withUsername("mate")
      .withPassword("mate1234");


}
