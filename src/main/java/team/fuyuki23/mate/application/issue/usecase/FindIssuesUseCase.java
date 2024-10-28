package team.fuyuki23.mate.application.issue.usecase;

import org.springframework.data.domain.Page;
import team.fuyuki23.mate.domain.Issue;
import team.fuyuki23.mate.domain.User;
import team.fuyuki23.mate.domain.vo.SI;

public interface FindIssuesUseCase {

  Result findIssues(Command command);

  record Command(SI si, User user, int page) {

  }

  record Result(Page<Issue> issues) {

  }

}
