package team.fuyuki23.mate.application.member.port.in;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.fuyuki23.mate.application.member.usecase.FindMembersByWorkspaceUseCase;
import team.fuyuki23.mate.common.exception.ApiException;

@Service
@RequiredArgsConstructor
public class FindMembersByWorkspaceInputPort implements FindMembersByWorkspaceUseCase {

  @Override
  public Result findMembersByWorkspace(Command command) throws ApiException {
    // TODO: Implement this method
    return new Result(
        null
    );
  }
}
