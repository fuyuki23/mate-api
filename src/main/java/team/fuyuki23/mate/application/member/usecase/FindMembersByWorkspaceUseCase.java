package team.fuyuki23.mate.application.member.usecase;

import java.util.List;
import team.fuyuki23.mate.common.exception.ApiException;
import team.fuyuki23.mate.domain.User;

public interface FindMembersByWorkspaceUseCase {

  Result findMembersByWorkspace(Command command) throws ApiException;

  record Command(String slug, User user) {

  }

  // FIXME: Please change User class to Member class
  record Result(List<User> members) {

  }

}
