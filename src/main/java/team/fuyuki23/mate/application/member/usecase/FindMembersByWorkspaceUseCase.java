package team.fuyuki23.mate.application.member.usecase;

import java.util.List;
import team.fuyuki23.mate.domain.Member;
import team.fuyuki23.mate.domain.User;

public interface FindMembersByWorkspaceUseCase {

  Result findMembersByWorkspace(Command command);

  record Command(String slug, User user) {
  }

  // FIXME: Please change User class to Member class
  record Result(List<Member> members) {
  }

}
