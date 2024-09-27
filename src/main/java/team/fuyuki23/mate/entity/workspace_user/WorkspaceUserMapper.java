package team.fuyuki23.mate.entity.workspace_user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.fuyuki23.mate.domain.Member;
import team.fuyuki23.mate.entity.user.UserMapper;
import team.fuyuki23.mate.entity.workspace.WorkspaceMapper;

@Component
@RequiredArgsConstructor
public class WorkspaceUserMapper {

    private final WorkspaceMapper workspaceMapper;
    private final UserMapper userMapper;

    public Member toDomain(WorkspaceUserJpaEntity workspaceUserJpaEntity) {
        return new Member(
                workspaceMapper.toDomain(workspaceUserJpaEntity.getWorkspace()),
                userMapper.toDomain(workspaceUserJpaEntity.getUser()),
                workspaceUserJpaEntity.getRole()
        );
    }

}
