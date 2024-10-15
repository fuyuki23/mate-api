package team.fuyuki23.mate.entity.issue;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import team.fuyuki23.mate.entity.common.BaseEntity;
import team.fuyuki23.mate.entity.project.ProjectJpaEntity;

@Table(name = "issue")
@Entity
@Getter
@Builder
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class IssueJpaEntity extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "workspace_id", columnDefinition = "binary(16)", nullable = false)
  private UUID workspaceId;

  @Column(name = "sequence_id", columnDefinition = "bigint", nullable = false)
  private Long sequenceId;

  @Column(name = "name", columnDefinition = "varchar", nullable = false)
  private String name;

  @Column(name = "description", columnDefinition = "text")
  private String description;

  @ManyToOne(optional = false)
  @JoinColumn(name = "project_id")
  @ToString.Exclude
  private ProjectJpaEntity project;

  @ManyToOne
  @JoinColumn(name = "parent_id")
  @ToString.Exclude
  private IssueJpaEntity parent;

}
