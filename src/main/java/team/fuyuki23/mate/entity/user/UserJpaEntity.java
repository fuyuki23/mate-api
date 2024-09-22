package team.fuyuki23.mate.entity.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import team.fuyuki23.mate.entity.common.BaseEntity;

@Table(name = "user")
@Entity
@Getter
@Builder
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserJpaEntity extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Setter
  @Column(name = "email", columnDefinition = "varchar", unique = true, nullable = false)
  private String email;

  @Setter
  @Column(name = "password", columnDefinition = "varchar", nullable = false)
  private String password;

  @Setter
  @Column(name = "first_name", columnDefinition = "varchar", length = 32, nullable = false)
  private String firstName;

  @Setter
  @Column(name = "last_name", columnDefinition = "varchar", length = 32, nullable = false)
  private String lastName;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    UserJpaEntity that = (UserJpaEntity) o;

    return id.equals(that.id);
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }

}
