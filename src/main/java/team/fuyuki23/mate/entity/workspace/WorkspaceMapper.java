package team.fuyuki23.mate.entity.workspace;

import org.springframework.stereotype.Component;
import team.fuyuki23.mate.domain.Workspace;

@Component
public class WorkspaceMapper {

    public Workspace toDomain(WorkspaceJpaEntity workspaceJpaEntity) {
        return new Workspace(
                workspaceJpaEntity.getId(),
                workspaceJpaEntity.getName(),
                workspaceJpaEntity.getSlug(),
                workspaceJpaEntity.getSize()
        );
    }

}
