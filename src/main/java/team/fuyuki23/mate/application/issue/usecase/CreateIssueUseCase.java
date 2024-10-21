package team.fuyuki23.mate.application.issue.usecase;

import team.fuyuki23.mate.domain.vo.SI;

public interface CreateIssueUseCase {

  Result createIssue(Command command);

  record Command(SI si, String title, String description) {

  }

  record Result(String id) {

  }

}
