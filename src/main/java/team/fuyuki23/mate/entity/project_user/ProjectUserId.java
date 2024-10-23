package team.fuyuki23.mate.entity.project_user;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ProjectUserId implements Serializable {

  @Serial
  private static final long serialVersionUID = 3959286612850468746L;

  @Column(name = "workspace_id", columnDefinition = "binary", length = 16, nullable = false)
  @EqualsAndHashCode.Include
  private UUID workspaceId;

  @Column(name = "project_id", columnDefinition = "binary", length = 16, nullable = false)
  @EqualsAndHashCode.Include
  private UUID projectId;

  @Column(name = "user_id", columnDefinition = "binary", length = 16, nullable = false)
  @EqualsAndHashCode.Include
  private UUID userId;

}
