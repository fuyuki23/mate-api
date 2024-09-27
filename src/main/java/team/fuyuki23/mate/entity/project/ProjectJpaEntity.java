package team.fuyuki23.mate.entity.project;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Objects;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import team.fuyuki23.mate.entity.common.BaseEntity;
import team.fuyuki23.mate.entity.user.UserJpaEntity;
import team.fuyuki23.mate.entity.workspace.WorkspaceJpaEntity;

@Table(name = "project")
@Entity
@Getter
@Builder
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ProjectJpaEntity extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Setter
  @Column(name = "identifier", columnDefinition = "varchar", length = 100, nullable = false)
  private String identifier;

  @Setter
  @Column(name = "name", columnDefinition = "varchar", length = 255, nullable = false)
  private String name;

  @Setter
  @Column(name = "description", columnDefinition = "text", nullable = true)
  private String description;

  @ManyToOne
  @JoinColumn(name = "workspace_id")
  @ToString.Exclude
  private WorkspaceJpaEntity workspace;

  @ManyToOne
  @JoinColumn(name = "leader_id")
  @ToString.Exclude
  private UserJpaEntity leader;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ProjectJpaEntity that)) {
      return false;
    }

    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }

}
