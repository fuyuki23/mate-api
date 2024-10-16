package team.fuyuki23.mate.entity.issue_state;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
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
import lombok.ToString;
import team.fuyuki23.mate.entity.common.BaseEntity;

@Table(name = "state")
@Entity
@Getter
@Builder
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class IssueStateJpaEntity extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "workspace_id", columnDefinition = "binary", length = 16, nullable = false)
  private UUID workspaceId;

  @Column(name = "project_id", columnDefinition = "binary", length = 16, nullable = false)
  private UUID projectId;

  @Column(name = "group", columnDefinition = "varchar", nullable = false)
  @Convert(converter = IssueStateGroupConverter.class)
  private IssueStateGroup group;

  @Column(name = "name", columnDefinition = "varchar", nullable = false)
  private String name;

  @Column(name = "description", columnDefinition = "text")
  private String description;

  @Column(name = "color", columnDefinition = "varchar", length = 7, nullable = false)
  private String color;

}
