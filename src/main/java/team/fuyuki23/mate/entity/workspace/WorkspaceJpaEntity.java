package team.fuyuki23.mate.entity.workspace;

import jakarta.persistence.*;
import lombok.*;
import team.fuyuki23.mate.entity.common.BaseEntity;

import java.util.UUID;

@Table(name = "workspace")
@Entity
@Getter
@Builder
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class WorkspaceJpaEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Setter
    @Column(name = "name", columnDefinition = "varchar", unique = true, nullable = false)
    private String name;

    @Setter
    @Column(name = "slug", columnDefinition = "varchar", unique = true, nullable = false)
    private String slug;

    @Setter
    @Column(name = "size", columnDefinition = "integer", nullable = false)
    private Integer size;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkspaceJpaEntity that = (WorkspaceJpaEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
