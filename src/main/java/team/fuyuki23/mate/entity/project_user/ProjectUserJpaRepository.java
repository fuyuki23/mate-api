package team.fuyuki23.mate.entity.project_user;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProjectUserJpaRepository extends
    JpaRepository<ProjectUserJpaEntity, ProjectUserId> {

  @Query("select pu from ProjectUserJpaEntity pu inner join fetch pu.workspace inner join fetch pu.project inner join pu.user where pu.projectUserId.workspaceId = :#{#id.workspaceId} and pu.projectUserId.projectId = :#{#id.projectId} and pu.projectUserId.userId = :#{#id.userId}")
  Optional<ProjectUserJpaEntity> findByProjectUserId(@Param("id") ProjectUserId id);

  @Query("select pu from ProjectUserJpaEntity pu inner join fetch pu.workspace inner join fetch pu.project inner join pu.user where pu.projectUserId.workspaceId = :workspaceId and pu.project.identifier = :identifier and pu.projectUserId.userId = :userId")
  Optional<ProjectUserJpaEntity> findByWorkspaceIdAndIdentifierAndUserId(UUID workspaceId,
      String identifier, UUID userId);

}
