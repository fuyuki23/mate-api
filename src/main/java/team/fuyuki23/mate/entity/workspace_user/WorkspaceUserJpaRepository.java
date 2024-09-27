package team.fuyuki23.mate.entity.workspace_user;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkspaceUserJpaRepository extends
    JpaRepository<WorkspaceUserJpaEntity, WorkspaceUserId> {

  @Query("select w from WorkspaceUserJpaEntity w inner join fetch w.workspace inner join fetch w.user where w.workspaceUserId.userId = :userId")
  List<WorkspaceUserJpaEntity> findByUserId(UUID userId);

  @Query("select w from WorkspaceUserJpaEntity w inner join fetch w.workspace inner join fetch w.user where w.workspaceUserId.workspaceId = :workspaceId")
  List<WorkspaceUserJpaEntity> findByWorkspaceId(UUID workspaceId);

  @Query("select w from WorkspaceUserJpaEntity w inner join fetch w.workspace inner join fetch w.user where w.workspaceUserId.workspaceId = :#{#id.workspaceId} and w.workspaceUserId.userId = :#{#id.userId}")
  Optional<WorkspaceUserJpaEntity> findByWorkspaceUserId(@Param("id") WorkspaceUserId id);

  @Query("select w from WorkspaceUserJpaEntity w inner join fetch w.workspace inner join fetch w.user where w.workspace.slug = :slug and w.workspaceUserId.userId = :userId")
  Optional<WorkspaceUserJpaEntity> findBySlugAndUserId(@Param("slug") String slug,
      @Param("userId") UUID userId);

}
