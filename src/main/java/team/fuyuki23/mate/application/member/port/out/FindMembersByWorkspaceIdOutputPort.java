package team.fuyuki23.mate.application.member.port.out;

import java.util.List;
import java.util.UUID;
import team.fuyuki23.mate.domain.Member;

public interface FindMembersByWorkspaceIdOutputPort {

  List<Member> findMembersByWorkspaceId(UUID workspaceId);

}
