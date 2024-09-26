package team.fuyuki23.mate.entity.workspace_user;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import team.fuyuki23.mate.entity.user.UserJpaEntity;
import team.fuyuki23.mate.entity.workspace.WorkspaceJpaEntity;

@Table(name = "workspace_user")
@Entity
@Getter
@Builder
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class WorkspaceUserJpaEntity {

    @EmbeddedId
    private WorkspaceUserId workspaceUserId;

    @Column(name = "role", columnDefinition = "smallint", nullable = false)
    private int role;

    @CreatedDate
    @Column(name = "create_at", columnDefinition = "timestamp", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @MapsId("workspaceId")
    @JoinColumn(name = "workspace_id")
    @ToString.Exclude
    private WorkspaceJpaEntity workspace;

    @ManyToOne()
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private UserJpaEntity user;

}
