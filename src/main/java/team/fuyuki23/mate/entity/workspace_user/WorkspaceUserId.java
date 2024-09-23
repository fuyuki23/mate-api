package team.fuyuki23.mate.entity.workspace_user;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class WorkspaceUserId implements Serializable {

    @Serial
    private static final long serialVersionUID = 3959286632850468746L;

    @Column(name = "workspace_id", columnDefinition = "binary", length = 16, nullable = false)
    @EqualsAndHashCode.Include
    private UUID workspaceId;
    @Column(name = "user_id", columnDefinition = "binary", length = 16, nullable = false)
    @EqualsAndHashCode.Include
    private UUID userId;

}
