package team.fuyuki23.mate.entity.workspace_user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface WorkspaceUserJpaRepository extends JpaRepository<WorkspaceUserJpaEntity, WorkspaceUserId> {

    @Query("select w from WorkspaceUserJpaEntity w inner join fetch w.workspace inner join fetch w.user where w.workspaceUserId.userId = :userId")
    List<WorkspaceUserJpaEntity> findByWorkspaceUserIdUserId(UUID userId);

}
