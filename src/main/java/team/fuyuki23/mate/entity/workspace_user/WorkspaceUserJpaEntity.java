package team.fuyuki23.mate.entity.workspace_user;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import team.fuyuki23.mate.entity.user.UserJpaEntity;
import team.fuyuki23.mate.entity.workspace.WorkspaceJpaEntity;

import java.time.LocalDateTime;

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
    @Column(name = "create_at", columnDefinition = "timestamp", nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @MapsId("workspaceId")
    @JoinColumn(name = "workspace_id")
    @ToString.Exclude
    private WorkspaceJpaEntity workspace;

    @ManyToOne(optional = false)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private UserJpaEntity user;

}
