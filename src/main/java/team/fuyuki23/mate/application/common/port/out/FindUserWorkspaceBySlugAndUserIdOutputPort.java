package team.fuyuki23.mate.application.common.port.out;

import java.util.Optional;
import java.util.UUID;
import team.fuyuki23.mate.domain.Member;

public interface FindUserWorkspaceBySlugAndUserIdOutputPort {

  Optional<Member> findUserWorkspaceBySlugAndUserId(String slug, UUID userId);

}