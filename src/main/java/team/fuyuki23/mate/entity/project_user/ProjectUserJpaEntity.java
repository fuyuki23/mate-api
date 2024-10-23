package team.fuyuki23.mate.entity.project_user;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import team.fuyuki23.mate.entity.common.BaseEntity;
import team.fuyuki23.mate.entity.project.ProjectJpaEntity;
import team.fuyuki23.mate.entity.user.UserJpaEntity;
import team.fuyuki23.mate.entity.workspace.WorkspaceJpaEntity;

@Table(name = "project_user")
@Entity
@Getter
@Builder
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ProjectUserJpaEntity extends BaseEntity {

  @EmbeddedId
  private ProjectUserId projectUserId;

  @Column(name = "role", columnDefinition = "smallint", nullable = false)
  private int role;

  @ManyToOne
  @MapsId("workspaceId")
  @JoinColumn(name = "workspace_id")
  @ToString.Exclude
  private WorkspaceJpaEntity workspace;

  @ManyToOne
  @MapsId("projectId")
  @JoinColumn(name = "project_id")
  @ToString.Exclude
  private ProjectJpaEntity project;

  @ManyToOne()
  @MapsId("userId")
  @JoinColumn(name = "user_id")
  @ToString.Exclude
  private UserJpaEntity user;


}
